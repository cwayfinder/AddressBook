Ext.define('TD.data.GroupProxy', {
    extend: 'Ext.data.proxy.Rest',

    url: 'groups',
    reader: {
        type: 'json',
        root: 'data',
        totalProperty: 'totalCount'
    },
    writer: {
        type: 'json'
    },
    listeners: {
        exception: function(p, response, operation, eOpts) {
            if (response.status === 401) {
//                window.location.assign("/login.html");
				alert('401');
            }
        }
    },
    buildUrl: function() {
        return Ext.data.proxy.Rest.prototype.buildUrl.apply(this, arguments).replace('?', '.json?');
    }
});