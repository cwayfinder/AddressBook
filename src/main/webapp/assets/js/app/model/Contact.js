Ext.define('TD.model.Contact', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'int',
            useNull: true
        },
        'name',
        'email',
        'phone',
        'groupId'
    ],
    validations: [
        { type: 'length', field: 'name', min: 4 },
        { type: 'email', field: 'email' }
    ]
});