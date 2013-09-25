Ext.define('TD.data.ContactProxy', {
    extend: 'Ext.data.proxy.Rest',

    url: 'contacts',
    reader: {
        type: 'json',
        root: 'data',
        totalProperty: 'totalCount'
    },
    writer: {
        type: 'json'
    },
    buildUrl: function(request) {
        var url = Ext.data.proxy.Rest.prototype.buildUrl.apply(this, arguments).replace('?', '.json?');

        if (request.action != 'read') {
            url = url.replace(/contacts\/\d\//, '');
        }

        return  url;
    }
});