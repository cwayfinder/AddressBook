Ext.define('TD.view.ContactsPanel', {
	extend: 'Ext.panel.Panel',

	itemId: 'contactsPanel',

	region: 'center',
	layout: 'border',

    items: [
        Ext.create('TD.view.CenterPanel'),
        Ext.create('TD.view.WestPanel'),
        Ext.create('TD.view.TopPanel')
    ]
});