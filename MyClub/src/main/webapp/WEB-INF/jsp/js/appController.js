requirejs.config({
    // Path mappings for the logical module names
    baseUrl: "js",
    paths: {
    	'knockout': 'libs/knockout/knockout-3.4.0.debug',
        'komapping': 'libs/knockout/knockout.mapping-latest.debug',
        'jquery': 'libs/jquery/jquery-3.1.1',
        'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.12.0',
        'dnd-polyfill': 'libs/dnd-polyfill/dnd-polyfill-1.0.0.min',
        'promise': 'libs/es6-promise/es6-promise',
        'basemaps': 'libs/oj/v3.0.0/resources/internal-deps/dvt/thematicMap/basemaps',
        'ojs': 'libs/oj/v3.0.0/debug',
        'ojL10n': 'libs/oj/v3.0.0/ojL10n',
        'ojtranslations': 'libs/oj/v3.0.0/resources',
        'customElements': 'libs/webcomponents/CustomElements',
        'hammerjs': 'libs/hammer/hammer-2.0.8',
        'ojdnd': 'libs/dnd-polyfill/dnd-polyfill-1.0.0',
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


define(['knockout', 'ojs/ojcore', 'ojs/ojrouter', 'ojs/ojarraytabledatasource', 'ojs/ojmodule', 'ojs/ojmoduleanimations'],
  function(ko, oj) {
    function ControllerViewModel() {
      var self = this;
      // Save the theme so we can perform platform specific navigational animations
      var platform = oj.ThemeUtils.getThemeTargetPlatform();

      self.userId = userId;
      // Static demo list view data
      self.data = [];
      self.myData = [];
      /*for (var i = 0; i < 30; i++) {
        self.data.push({
          id: i, name: 'Customer ' + (i + 1), content: 'Customer ' + (i + 1) + ' info.'
        });
      }*/
      var searchStr ="name=" + "&startDate=" + "&endDate=" + "&deadline=";
      $.ajax({
			url : "activitySearch",
			type : "post",
			data : searchStr, 
			success:function(data){ 
				for (var i = 0; i < data.response.length; i++) {
			        self.data.push({
			          id: data.response[i].id, 
			          name: data.response[i].name, 
			          startDate : data.response[i].startDate,
			          endDate : data.response[i].endDate,
			          status : data.response[i].status,
			          deadline : data.response[i].deadline,
			          description : data.response[i].description
			        })
				}
			 }
			});
      $.ajax({
			url : "myActivities",
			type : "get",
			success:function(data){ 
				for (var i = 0; i < data.activitys.length; i++) {
			        self.myData.push({
			          id: data.activitys[i].id, 
			          name: data.activitys[i].name, 
			          startDate : data.activitys[i].startDate,
			          endDate : data.activitys[i].endDate,
			          status : data.activitys[i].status,
			          deadline : data.activitys[i].deadline,
			          description : data.activitys[i].description
			        })
				}
			 }
			});
      // Nav Bar
      var navData = [
       {name: 'All Activities', id: 'customers',
         iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-chart-icon-24'},
       {name: 'My Activities', id: 'myActivities',
         iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-fire-icon-24'},
         {name: 'User Profile', id: 'userProfile',
             iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-fire-icon-24'}
      ];
      self.navDataSource = new oj.ArrayTableDataSource(navData, {idAttribute: 'id'});
      self.navBarChange = function(event, ui) {
        if (ui.option === 'currentItem' && ui.value != null) {
          if (platform === 'android') {
            self.pendingAnimationType = 'fade';
          } else {
            self.pendingAnimationType = null;
          }
        }
      }

      // Router setup
      self.router = oj.Router.rootInstance;
      self.router.configure({
       'myActivities': {label: 'My Activities', value: {'level': 1}},
       'incidents': {label: 'Incidents', value: {'level': 1}},
       'userProfile': {label: 'User Profile', value: {'level': 1}},
       'customers': {label: 'All Activities', value: {'level': 1}, isDefault: true},
       'customerInfo': {label: 'Customer Info', value: {'level': 2}},
       'activityInfo': {label: 'Activity Info', value: {'level': 2}}
      });
      oj.Router.defaults['urlAdapter'] = new oj.Router.urlParamAdapter();

      // Animations
      self.pendingAnimationType = null; // Animation type controlled by view
      // Callback function that can return different animations based on application logic.
      function switcherCallback(context) {
        return self.pendingAnimationType;
      };
      self.preDrill = function() {
        // Temporarily adjust fixed top element to absolute position for animation
        var topElems = document.getElementsByClassName('oj-applayout-fixed-top');
        for (var i = 0; i < topElems.length; i++) {
          // Toggle between absolute and fixed positioning so we can animate the header.
          // We don't need to adjust for scrolled content here becaues the animation utility
          // moves the contents to a transitional div losing the scroll position
          topElems[i].style.position = "absolute";
        }
      }
      self.postDrill = function() {
        var view = document.getElementById(self.router.stateId());
        var topElem = view.getElementsByClassName('oj-applayout-fixed-top')[0];
        topElem.style.position = "fixed";

        // After drilling into the customer info, move focus to the back button for accessibility
        $('#backBtn').focus();
      }
      function mergeConfig(original) {
        return $.extend(true, {}, original, {
          'params': { 'content': self.data },
          'animation': oj.ModuleAnimations.switcher(switcherCallback)
        });
      }
      self.moduleConfig = mergeConfig(self.router.moduleConfig);
      self.showNavBar = function() {
        if (platform === 'android') {
          var navbar = document.getElementsByClassName('oj-applayout-fixed-bottom')[0];
          navbar.style.visibility = "visible";
        }
      }
      self.hideNavBar = function() {
        if (platform === 'android') {
          var navbar = document.getElementsByClassName('oj-applayout-fixed-bottom')[0];
          navbar.style.visibility = "hidden";
        }
      }

      // Header Model
      function goBack() {
        self.pendingAnimationType = 'navParent';
        // Fade out back button
        fadeBackButton();
        window.history.back();
      }
      function fadeBackButton() {
        if (platform === 'ios') {
          var backBtn = document.getElementById('backBtn');
          if (backBtn) {
            oj.AnimationUtils[self.pendingAnimationType === 'navParent' ? 'fadeOut' : 'fadeIn'](backBtn, {'persist': 'all'});
          }
        }
      }
      self.getHeaderModel = function() {
        var headerFactory = {
          createViewModel: function(params, valueAccessor) {
            var model =  {
              router: self.router,
              level: self.router.currentValue().level,
              pageTitle: self.router.currentState().label,
              goBack: goBack,
              handleBindingsApplied: function(info) {
                // Hide back button before fade in animation
                if (platform === 'ios') {
                  var backBtn = document.getElementById('backBtn');
                  if (backBtn && self.pendingAnimationType === 'navChild') {
                    backBtn.style.opacity = 0;
                  }
                }
                self.adjustContentPadding();
                fadeBackButton();
              }
            };
            return Promise.resolve(model);
          }
        }
        return headerFactory;
      }

      // Method for adjusting the content area top/bottom paddings to avoid overlap with any fixed regions. 
      // This method should be called whenever your fixed region height may change.  The application
      // can also adjust content paddings with css classes if the fixed region height is not changing between 
      // views.
      self.adjustContentPadding = function () {
        // During transition animation there are two Views on the page so we want to make sure we update the
        // current view
        var view = document.getElementById(self.router.stateId());
        var topElem = view.getElementsByClassName('oj-applayout-fixed-top')[0];
        var contentElem = view.getElementsByClassName('oj-applayout-content')[0];
        var bottomElem = document.getElementsByClassName('oj-applayout-fixed-bottom')[0];

        if (topElem) {
          contentElem.style.paddingTop = topElem.offsetHeight+'px';
        }
        if (bottomElem) {
          contentElem.style.paddingBottom = bottomElem.offsetHeight+'px';
        }
        // Add oj-complete marker class to signal that the content area can be unhidden.
        // See the CSS demo tab to see when the content area is hidden.
        contentElem.classList.add('oj-complete');
      }
    }

    return new ControllerViewModel();
  }
);
