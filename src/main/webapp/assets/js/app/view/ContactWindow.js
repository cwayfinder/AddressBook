Ext.define('TD.view.ContactWindow', {
    extend: 'Ext.window.Window',

    title: 'Contact',
    closable: true,
    closeAction: 'hide',
    width: 350,
    minWidth: 350,
    height: 200,

    layout: 'fit',
    items: [{
        xtype: 'form',
        bodyPadding: 5,
        items: [{
            labelWidth: 50,
            fieldLabel: 'Name',
            width: 300,
            xtype: 'textfield',
            name: 'name'
        }, {
            labelWidth: 50,
            fieldLabel: 'Email',
            width: 300,
            xtype: 'textfield',
            name: 'email'
        }, {
            labelWidth: 50,
            fieldLabel: 'Phone',
            width: 300,
            xtype: 'numberfield',
            name: 'phone'
        }]
    }],
    bbar: ['->', {
        xtype: 'button',
        action: 'saveContact',
        text: 'Save'
    }, {
        xtype: 'button',
        text: 'Cancel',
        handler: function (btn) {
            btn.up('window').hide();
        }
    },
        '->'],

    editContact: function (contact) {
        this.show();
        this.down('form').getForm().loadRecord(contact);
    }
});