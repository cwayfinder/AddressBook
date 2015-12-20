Ext.define('TD.store.Groups', {
    extend: 'Ext.data.Store',

    autoLoad: true,
    autoSync: true,

    pageSize: 50,
    model: 'TD.model.Group',
    proxy: Ext.create('TD.data.GroupProxy')
});