Ext.define('TD.view.GroupsWindow', {
    extend: 'Ext.window.Window',

    title: 'Groups',
    closable: true,
    closeAction: 'hide',
    width: 600,
    minWidth: 350,
    height: 350,

    layout: 'fit',
    items: [
        {
            xtype: 'gridpanel',
            store: Ext.data.StoreManager.lookup('groupsStore'),
            plugins: [Ext.create('Ext.grid.plugin.RowEditing', {
                pluginId: 'rowEditing',
                listeners: {
                    cancelEdit: function (rowEditing, context) {
                        // Canceling editing of a locally added, unsaved record: remove it
                        if (context.record.phantom) {
                            rowEditing.getGrid().getStore().remove(context.record);
                        }
                    }
                }
            })],
            columns: [
                {
                    text: 'Name',
                    flex: 1,
                    sortable: true,
                    dataIndex: 'name',
                    field: {
                        xtype: 'textfield',
                        width: '100%'
                    }
                }
            ]
        }
    ],
    bbar: [ '->', {
        xtype: 'button',
        action: 'addGroup',
        text: 'Add'
    }, {
        xtype: 'button',
        action: 'removeGroup',
        text: 'Remove'
    },
        '->' ]
});