Ext.define('TD.controller.Contact', {
    extend: 'Ext.app.Controller',

    refs: [{
        ref: 'contactDetailsPanel',
        selector: '#contactDetailsPanel'
    }, {
        ref: 'mainPanel',
        selector: 'viewport > panel'
    }, {
        ref: 'groupCombobox',
        selector: 'combobox[name=group]'
    }, {
        ref: 'contactsGrid',
        selector: '#contactsListPanel > gridpanel'
    }],

    init: function() {
        'use strict';

        Ext.data.StoreManager.lookup('groupsStore').addListener('load', function(store, records) {
            if (this.getGroupCombobox() && !this.getGroupCombobox().getValue() && records.length) {
                this.getGroupCombobox().select(records[0]);
            }
        }, this);
        Ext.data.StoreManager.lookup('groupsStore').addListener('update', function(store, record) {
            if (this.getGroupCombobox() && !this.getGroupCombobox().getValue() && record) {
                this.getGroupCombobox().select(record);
            }
        }, this);
        Ext.data.StoreManager.lookup('groupsStore').addListener('remove', function(store, records) {
            if (this.getGroupCombobox() && store.getCount() == 0) {
                this.getGroupCombobox().clearValue();
            }
        }, this);

        var groupsWindow = Ext.create('TD.view.GroupsWindow');
        var contactWindow = Ext.create('TD.view.ContactWindow');

        this.control({
            'button[action=manageGroups]': {
                click: function(btn) {
                    groupsWindow.show();
                }
            },
            'combobox[name=group]': {
                change: function (combo, newValue, oldValue, eOpts ) {
                    Ext.data.StoreManager.lookup('contactsStore').groupId = newValue;
                    Ext.data.StoreManager.lookup('contactsStore').load();
                }
            },
            '#contactsListPanel > gridpanel': {
                selectionchange: function(model, records) {
                    if (records.length) {
                        this.getContactDetailsPanel().setVisible(true);
                        this.getContactDetailsPanel().setValue(records[0]);
                    }
                }
            },
            '[action=addContact]': {
                click: function(btn) {
                    var contact = Ext.create('TD.model.Contact');
                    contact.set('groupId', this.getGroupCombobox().getValue());
                    Ext.data.StoreManager.lookup('contactsStore').insert(0, contact);
                    contactWindow.editContact(contact);
                }
            },
            '[action=editContact]': {
                click: function(btn) {
                    var contact = this.getContactsGrid().getSelectionModel().getSelection()[0];
                    contactWindow.editContact(contact);
                }
            },
            '[action=removeContact]': {
                click: function(btn) {
                    var selection = btn.up('panel').down('gridpanel').getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                        Ext.data.StoreManager.lookup('contactsStore').remove(selection);
                    }
                }
            },
            '[action=saveContact]': {
                click: function(btn) {
                    var form = btn.up('window').down('form').getForm();
                    var record = form.updateRecord().getRecord();

                    var errors = form.updateRecord(form.getRecord()).getRecord().validate();  // voodoo!
                    if(errors.isValid()) {
                        this.getContactDetailsPanel().setValue(record);
                        contactWindow.hide();
                    } else {
                        form.markInvalid(errors);
                    }
                }
            },
            '[action=addGroup]': {
                click: function(btn) {
                    Ext.data.StoreManager.lookup('groupsStore').insert(0, Ext.create('TD.model.Group'));
                    btn.up('panel').down('gridpanel').getPlugin('rowEditing').startEdit(0, 0);
                }
            },
            '[action=removeGroup]': {
                click: function(btn) {
                    var selection = btn.up('panel').down('gridpanel').getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                        Ext.data.StoreManager.lookup('groupsStore').remove(selection);
                    }
                }
            },
            'textfield[name=query]': {
                specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        Ext.data.StoreManager.lookup('contactsStore').load({
                            query: field.getValue()
                        });
                    }
                }
            }
        });
    }
});