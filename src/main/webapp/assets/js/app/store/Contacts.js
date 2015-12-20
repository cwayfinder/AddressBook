Ext.define('TD.store.Contacts', {
    extend: 'Ext.data.Store',

    autoLoad: true,
    autoSync: true,

    pageSize: 50,
    model: 'TD.model.Contact',
    proxy: Ext.create('TD.data.ContactProxy'),
//	sorters: [{
//		property: 'name',
//		direction: 'ASC'
//	}],

    groupId: null,

    load: function (config) {
        if (this.groupId) {
            this.getProxy().url = 'groups/' + this.groupId + '/contacts' + (config && config.query ? '?query=' + config.query : '');
            this.callParent();
            this.getProxy().url = 'contacts';
        }
    }
});