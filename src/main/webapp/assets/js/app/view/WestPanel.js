Ext.define('TD.view.WestPanel', {
    extend: 'Ext.panel.Panel',

    itemId: 'contactsListPanel',

    title: 'Contacts List',
    region: 'west',
    width: 300,
    minWidth: 80,
    split: true,
    collapsible: true,
    collapseMode: 'mini',

    layout: {
        type:'vbox',
        padding:'5',
        align:'stretch'
    },
    items: [ {
        xtype: 'textfield',
        name: 'query',
        emptyText: 'Search you contacts'
    }, {
        xtype: 'panel',
        layout: 'hbox',
        items: [{
            xtype: 'combobox',
            flex: 1,
            name: 'group',
            store: Ext.data.StoreManager.lookup('groupsStore'),
            valueField: 'id',
            displayField: 'name',
            emptyText: 'Click "..." button to manage groups'
        }, {
            xtype: 'button',
            action: 'manageGroups',
            text: '...'
        } ]
    }, Ext.create('TD.view.GridPanel', {
        flex: 1,
        border: true,
        store: Ext.data.StoreManager.lookup('contactsStore')
    }) ],
    bbar: [ '->', {
        xtype: 'button',
        action: 'addContact',
        text: 'Add'
    }, {
        xtype: 'button',
        action: 'removeContact',
        text: 'Remove'
    },
    '->' ]
});