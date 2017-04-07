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


require(['jquery', 'knockout', 'ojs/ojcore', 'ojs/ojinputtext', 'ojs/ojknockout', 'ojs/ojbutton', 'ojs/ojknockout-validation'], function ($, ko) {
    function rootViewModel() {
        var self = this;
        self.labels = {
            "logoTitle": "Oracle Logo",
            "appName": " My Club",
            "userName": "Username:",
            "password": "Password:",
            "actionLable": "Login"
        };
        self.username = ko.observable("");
        self.password = ko.observable("");
        self.errorMessage = ko.observable();
        self.tracker = ko.observable();
        var queryString = window.location.search;
        if(queryString && queryString.length > 0){
          queryString = queryString.substring(1);
          var queries = queryString.split("&");
          for(var i=0; i < queries.length; i++){
              var queryPair = queries[i].split("=");
              if(queryPair[0] === "expiry" && queryPair[1] ==="true"){
                  self.errorMessage("Session expired. Please login again.");
                  break;
              }
          }
        }

        self.doLogin = function () {

            self.errorMessage("");
            var trackerObj = ko.utils.unwrapObservable(self.tracker);
            trackerObj.showMessages();
            if (!trackerObj.focusOnFirstInvalid()) {
                $.ajax({
                    method: "POST",
                    url: "/login",
                    data: {
                        username: self.username(),
                        password: self.password()
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("csrf-token", sessionStorage.token);
                    },
                    dataType: "json",
                }).done(function (response) {
                    if (response.validuser) {
                        sessionStorage.removeItem("token");
                        sessionStorage.removeItem("accessToken");
                        sessionStorage.removeItem("userId");
                        localStorage.removeItem("userName");
                        localStorage.userName = self.username()[0].toUpperCase() + self.username().slice(1).toLowerCase();
                        window.location = "/index.html" + window.location.hash;
                    } else {
                        self.errorMessage(response.error);
                    }
                }).fail(function (jqXhr, textStatus, errorThrown) {
                    self.errorMessage("Authentication failed");
                });
            }
        };


    }
    $(document).ready(function () {
    	  ko.applyBindings(new rootViewModel());
       /* $.get("/user/token").done(function (data) {
            if (data && data.csrfToken) {
                sessionStorage.token = data.csrfToken;
                ko.applyBindings(new rootViewModel());
            } else {
                window.location = "/logout";
            }
        }).fail(function (data) {
            window.location = "/logout";
        });*/

    });


});
