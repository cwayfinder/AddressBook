Ext.define('TD.view.TopPanel', {
	extend: 'Ext.toolbar.Toolbar',

	itemId: 'topPanel',

	region: 'north',
	border: true,
	items: [{
		xtype: 'box',
		autoEl: {tag: 'span', html: 'Address Book'}
	},'->', {
		xtype: 'box',
		autoEl: {tag: 'span', name: 'userName', html: 'userName'}
	}, ' ', {
		xtype: 'button',
		text: 'Logout',
		action: 'logout'
	}],

	setUserName: function(userName) {
		this.getEl().down('span[name=userName]').setHTML(userName);
	}
});