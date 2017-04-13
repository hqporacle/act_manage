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

define(['knockout', 'appController', 'ojs/ojinputtext'],
  function(ko, app) {
    function ViewModel() {

      var self = this;
      self.headerConfig = {'viewName': 'myActivityInfoHeader', 'viewModelFactory': app.getHeaderModel()};

      self.feedback = ko.observable();
      // Setup child router
      self.handleActivated = function(info) {
        if (self.router) {
          return;
        }

        // Retrieve parentRouter from module params
        var parentRouter = info.valueAccessor().params.ojRouter.parentRouter;
        // Retrieve list data from module params
        self.data = info.valueAccessor().params.content;

        self.router = parentRouter.createChildRouter('myId').configure(function (stateId) {
          var state;
          self.activityId = stateId;
          if (stateId) {
            state = new oj.RouterState(
              stateId,
              {
                enter: function() {
                  self.startDate = app.data[parseInt(stateId)-1].startDate,
                  self.endDate = app.data[parseInt(stateId)-1].endDate,
                  self.status = app.data[parseInt(stateId)-1].status,
                  self.deadline = app.data[parseInt(stateId)-1].deadline,
                  self.description = app.data[parseInt(stateId)-1].description
                } 
              },
               self.router
            );
          }
          return state;
        });

        return oj.Router.sync();
      }

      self.quit = function() {
    	  var str = "userId=" + userId + "&activityId=" + self.activityId;
      	$.ajax({
				url : "participantsQuit",
				type : "post",
				data : str, 
				success:function(data){ 
					 console.log("Join successfully!");
					}
				});
      };
      
      self.submitFeedback = function() {
    	  var str = "userId=" + userId + "&activityId=" + self.activityId + "&status=1" + "&feedback=" + self.feedback();
      	$.ajax({
				url : "updateFeedback",
				type : "post",
				data : str, 
				success:function(data){ 
					 console.log("Join successfully!");
					}
				});
      };
      
      self.handleBindingsApplied = function(info) {
        if (app.pendingAnimationType === 'navChild') {
          app.hideNavBar();
          app.preDrill();
        }
      }

      self.handleTransitionCompleted = function(info) {
        if (app.pendingAnimationType === 'navChild') {
          app.postDrill();
        }
      }

    }

    return new ViewModel();
  }
);
