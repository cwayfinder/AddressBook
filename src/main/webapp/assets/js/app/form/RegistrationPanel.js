Ext.define('TD.form.RegistrationPanel', {
    extend: 'Ext.form.Panel',

    itemId: 'registrationPanel',

    width: 400,
    layout: 'form',
    frame: true,
    border: false,
    bodyPadding: 10,
    title: "Sign Up",

    url: 'registration.json',

    fieldDefaults: {
        labelWidth: 100
    },
    items: [{
        xtype: 'textfield',
        fieldLabel: 'Email',
        name: 'email',
        vtype: 'email',
        allowBlank: false
    }, {
        xtype: 'fieldset',
        name: 'emailErrors',
        border: false,
        columnWidth: 0.5,
        defaultType: 'displayfield',
        defaults: {
            hideLabel: true,
            anchor: '100%',
            margin: '-5 0 -5 100'
        },
        layout: 'anchor'
    }, {
        xtype: 'textfield',
        fieldLabel: 'Password',
        allowBlank: false,
        name: 'password',
        inputType: 'password'
    }, {
        xtype: 'fieldset',
        name: 'passwordErrors',
        border: false,
        columnWidth: 0.5,
        defaultType: 'displayfield',
        defaults: {
            hideLabel: true,
            anchor: '100%',
            margin: '-5 0 -5 100'
        },
        layout: 'anchor'
    }],
    fbar: [{
        action: 'showLoginPanel',
        text: 'Use existing account'
    }, '->', {
        text: 'Sign Up',
        action: 'signUp'
    }]
});