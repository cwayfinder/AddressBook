Ext.define('TD.container.Viewport', {
	extend: 'Ext.container.Viewport',

	layout: 'border',
	items: [
		Ext.create('TD.view.MainPanel')
//		Ext.create('TD.view.ContactsPanel')
	]
});