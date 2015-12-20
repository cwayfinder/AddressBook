Ext.Loader.setConfig({
    enabled: true,         // Allow dynamic loading of JavaScript files
    disableCaching: true,  // Disable random parameter in the URL's path
    paths: {
        'Ext': '.',
        'TD': '/AddressBook/assets/js/app'
//		'TD': '/assets/js/app'
    }
});

Ext.require([
    'TD.model.User',
    'TD.model.Contact',
    'TD.model.Group',
    'TD.data.GroupProxy',
    'Ext.grid.plugin.RowEditing'
]);

var user;

Ext.onReady(function () {
    console.log('gdgd');
    var viewport = Ext.create('TD.container.Viewport');

    Ext.create('TD.store.Groups', {
        storeId: 'groupsStore'
    });
    Ext.create('TD.store.Contacts', {
        storeId: 'contactsStore'
    });


    var authController = Ext.create('TD.controller.Auth');
    authController.init();

    var contactController = Ext.create('TD.controller.Contact');
    contactController.init();


    Ext.Ajax.request({
        url: 'user.json',
        success: function (response) {
            var text = response.responseText;
            var json = Ext.decode(text);

            if (json.success) {
                user = Ext.create('TD.model.User', json.data);

//				viewport.down('panel').removeAll();
                viewport.removeAll();
                viewport.add(Ext.create('TD.view.ContactsPanel'));

                viewport.down('#topPanel').setUserName(user.get('email'));

                Ext.data.StoreManager.lookup('groupsStore').loadPage(1);
            } else {
                viewport.down('panel').add(Ext.create('TD.form.LoginPanel'));
            }
        }
    });
});