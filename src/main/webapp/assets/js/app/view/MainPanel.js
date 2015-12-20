Ext.define('TD.view.MainPanel', {
    extend: 'Ext.panel.Panel',

    itemId: 'mainPanel',

    region: 'center',
    layout: {
        type: 'vbox',
        align: 'center',
        pack: 'center'
    },
    autoScroll: true,
    viewConfig: {
        autoScroll: true
    }
});