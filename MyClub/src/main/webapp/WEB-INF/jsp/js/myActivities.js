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

define(['knockout', 'appController', 'ojs/ojarraytabledatasource', 'ojs/ojlistview'],
  function(ko, app) {
    function ViewModel(moduleParams) {
      
      var self = this;      
      self.headerConfig = {'viewName': 'header', 'viewModelFactory': app.getHeaderModel()};

      // Pass the document body to the ojListView's scrollPolicyOptions to allow for scroll to top
      // when tapping on the status bar for iOS
      self.scrollElem = document.body;
      
      self.dataSource = new oj.ArrayTableDataSource(app.data, {idAttribute: 'id'});

      self.gotoContent = function(event, ui) {
         if (ui.option === 'currentItem' && ui.value != null) {
            app.pendingAnimationType = 'navChild';
            moduleParams.ojRouter.parentRouter.go('activityInfo/' + ui.value)
         }
      };

      self.handleBindingsApplied = function(info) {
        if (app.pendingAnimationType === 'navParent') {
          app.preDrill();
        }
      }

      self.handleTransitionCompleted = function(info) {
        if (app.pendingAnimationType === 'navParent') {
          app.showNavBar();
          app.postDrill();
        }
      }

    }

    return ViewModel;
  }
);
