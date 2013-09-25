Ext.define('TD.model.User', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'int',
        useNull: true
    }, 'email'],
    validations: [{
        type: 'length',
        field: 'email',
        min: 1
    }]
});