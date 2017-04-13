requirejs.config({
    // Path mappings for the logical module names
    baseUrl: "js",
    paths: {
        'knockout': 'libs/knockout/knockout-3.4.0.debug',
        'komapping': 'libs/knockout/knockout.mapping-latest.debug',
        'jquery': 'libs/jquery/jquery-3.1.1',
        'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.12.0',
        'promise': 'libs/es6-promise/es6-promise',
        'ojs': 'libs/oj/v3.0.0/debug',
        'customElements': 'libs/webcomponents/CustomElements',
        'ojL10n': 'libs/oj/v3.0.0/ojL10n',
        'ojtranslations': 'libs/oj/v3.0.0/resources',
        'hammerjs': 'libs/hammer/hammer-2.0.8',
        'signals': 'libs/js-signals/signals',
        'text': 'libs/require/text'
    },
    // Shim configurations for modules that do not expose AMD
    shim: {
        'jquery': {
            exports: ['jQuery', '$']
        },
        komapping: {
            deps: ['knockout'],
            exports: 'komapping'
        }
    }
});

require(['ojs/ojcore', 'knockout', 'jquery', 'appController', 'ojs/ojknockout', 'ojs/ojbutton',
  'ojs/ojnavigationlist', 'ojs/ojmodule', 'ojs/ojrouter'],
  function(oj, ko, $, app) {

    // Change the default location for the viewModel and view files
    oj.ModuleBinding.defaults.modelPath = '';
    oj.ModuleBinding.defaults.viewPath = 'text!../html/';
    
    $(function() {
       oj.Router.sync().then(function() {
          ko.applyBindings(app, document.getElementById('page'));
       });
    });
});