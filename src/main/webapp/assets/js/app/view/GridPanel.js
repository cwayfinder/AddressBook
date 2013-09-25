Ext.define('TD.view.GridPanel', {
    extend: 'Ext.grid.Panel',

    plugins: [Ext.create('Ext.grid.plugin.RowEditing', {
        pluginId:'rowEditing',
        listeners: {
            cancelEdit: function(rowEditing, context) {
                // Canceling editing of a locally added, unsaved record: remove it
                if (context.record.phantom) {
                    rowEditing.getGrid().getStore().remove(context.record);
                }
            }
        }
    })],
    columns: [{
        text: 'Name',
        flex: 1,
        sortable: true,
        dataIndex: 'name',
        field: {
            xtype: 'textfield',
            width: '100%'
        }
    }]
});