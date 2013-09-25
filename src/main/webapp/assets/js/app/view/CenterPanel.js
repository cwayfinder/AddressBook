Ext.define('TD.view.CenterPanel', {
    extend: 'Ext.form.Panel',

    itemId: 'contactDetailsPanel',

    hidden: true,

    title: 'Contact Details',
    region: 'center',
    flex: 1,

    layout: {
        type:'vbox',
        padding:'5'
    },

    items: [ {
        xtype: 'displayfield',
        name: 'name'
    }, {
        labelWidth: 50,
        fieldLabel: 'Email',
        xtype: 'displayfield',
        name: 'email'
    }, {
        labelWidth: 50,
        fieldLabel: 'Phone',
        xtype: 'displayfield',
        name: 'phone'
    } ],
    bbar: [{
        xtype: 'button',
        action: 'editContact',
        text: 'Edit'
    }],

    setValue: function(value) {
        if (value) {
            this.down('displayfield[name=name]').setValue(value.get('name')
                ? '<span style="font-size: 20px;">' + value.get('name') + '</span>'
                : '<span style="color: lightslategray;">N/A</span>')
            this.down('displayfield[name=email]').setValue(value.get('email') ? value.get('email') : '<span style="color: lightslategray;">N/A</span>')
            this.down('displayfield[name=phone]').setValue(value.get('phone') ? value.get('phone') : '<span style="color: lightslategray;">N/A</span>')
        }
    }
});