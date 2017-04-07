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


define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojinputtext', 'ojs/ojdatetimepicker', 'ojs/ojdialog', 'ojs/ojknockout', 'ojs/ojmenu', 'ojs/ojtabs', 'ojs/ojconveyorbelt', 'ojs/ojcollapsible',
        'ojs/ojselectcombobox', 'ojs/ojbutton'],
        function (oj, ko, $) {

            var PROPERTY_DEFS = {
                NAME: {display: 'Name'},
                STARTED_AT: {display: 'Start Time'},
                ENDED_AT: {display: 'End Time'},
                DESCRIPTION: {display: 'Description'},
                DEADLINE: {display: 'Deadline'},
                STATUS: {display: 'Status'},
            };

            var DEFAULT_ITEMS_SHOWN = 25;
            var ITEM_SHOWN_INCREMENT = 25;
            /*
             * Represents the search list, contains SearchListItems.
             * 
             * The component subscribes to the "RequestSearch" event. When an
             * event it received, the component uses the text from the event
             * to perform a query.
             * 
             * The results of the query are than placed into the observable 
             * results array, and the view should update automatically.
             */
            function SearchResultsViewModel(route, jsonData) {
            	//TODO:???
                route = route || {};

                var self = this;
                self.results = ko.observableArray();
                self.searchMessage = ko.observable();
                self.searchValue = ko.observable();
                
                self.name = ko.observable();
                self.description = ko.observable();
                self.startTime = ko.observable();
                self.endTime = ko.observable();
                //self.edit = ko.observable(false);

                
                self.label = {
                	create: 'Create+',
                    searchResults: 'searchResult',
                    showResults: function (num1, num2) {
                        return 'Showing ' + [num1] + ' of ' + [num2];
                    },
                    resultsFound: function (num1) {
                        return 'results Found' + [num1];
                    },
                    showMore: 'Show More',
                    showAll: 'Show All',
                    infoSummaryProp: 'Information Summary',
                    searchFor: 'Search For',
                    noResultsFound: 'No Results Found',
                    all: 'Search Results all',
                    editClub: 'Edit Club',
                    name: 'Name',
                    description: 'Description',
                    startDate: 'Start Date',
                    endDate: 'End Date',
                    save: 'Save',
                    cancel: 'Cancel',
                    startTimeError: 'Error'
                };
                self.doSearch = function() {
                	
                };
                
                self.doCreate = function() {
                	self.name("");
                    self.description("");
                    self.startTime("");
                    self.endTime("");
                	//self.edit = false;
                	$('#editDialog').ojDialog('open');
                	$('#text-input1').ojInputText({'disabled': false});
                };
                self.saveActivity = function() {
                	
                };
                self.cancelActivity = function() {
                	$('#editDialog').ojDialog('close');
                };
                self.message = {
                        hint : 'Search'
                };
                var numItemsShown = {
                    All: ko.observable(DEFAULT_ITEMS_SHOWN),
                }

                self.resultsAll = ko.pureComputed(function () {
                    return self.results().filter(function (result) {
                        return true;
                    });
                });
                self.resultsAllVisible = ko.pureComputed(function () {
                    return self.resultsAll().slice(0, numItemsShown.All());
                });

                var latestSearch = null;

                self.showMore = function () {
                    numItemsShown.All(numItemsShown.All() + ITEM_SHOWN_INCREMENT);
                }

                self.showAll = function () {
                    numItemsShown.All(10000);
                }

                this.handleMenuOption = function (event, ui)
                {
                    var menuElem = $(ui.item[0]);
                    var parElem = menuElem.parent();

                    //get params
                    var action = menuElem.data("action");
                    var data = JSON.parse(parElem.attr("data"));
                    console.log(action + " : " + data.id);

                    switch (action) {
                    case "edit":
                    	$.ajax({
            				url : "activityAdd",
            				type : "post",
            				data : "test", 
            				success:function(data){ 
            					console.log("success");
            					}
            				});	
                    	self.name("test");
                        self.description("Spring Outing");
                        /*self.startTime("04/12/17 11:00 a");
                        self.endTime("04/14/17 11:00 p");*/                      
                    	$('#editDialog').ojDialog('open');
                    	$('#text-input1').ojInputText({'disabled': true});
                    	
                    	break;
                    case "publish":
                    	break;
                    case "delete":
                    	break;
                    case "join":
                    	break;
                    }
                    //perform action
                    //TODO: action?
                   // handle[action](data, latestSearch);
                };

                self.handleMenuClick = function () {
                      // TODO: url
                       /* bdpApp.xhr("POST", url,
                                JSON.stringify({
                                    "objectId": item.id
                                }),
                                {
                                    showLoading: false, showGlobalErrorDialog: false,
                                    doneHandler: function (result) {
                                        $("#" + item.id).ojMenu("refresh");
                                    }
                                }
                        );*/
                	console.log("Test: ");
                }
                
                self.closeEditDialog = function () {
                    $('#editDialog_' + tabId).ojDialog('close');
                };
                
                self.validateStartTime = function () {
                    return {
                        validate: function (value)
                        {                            
                            value = value ? value.trim() : "";                            
                            self.tempScheduleStartTime = value;
                            var startTime = oj.IntlConverterUtils.isoToLocalDate(value);
                            var endTime = oj.IntlConverterUtils.isoToLocalDate(self.tempScheduleEndTime);
                            if (startTime >= endTime)
                            {
                                throw new oj.ValidatorError(self.label.startTimeError);
                            }
                            return true;
                        }
                    };
                };
                
                self.validateEndTime = function () {

                    return {
                        validate: function (value)
                        {
                            self.tempScheduleEndTime = value;                            
                        }
                    };
                };
                
                init(jsonData, route.text);

                function getNameFilterURLParams(text)
                {
                    return !text ? ":" : window.encodeURIComponent(text);
                }

                //TODO: url should be hard code.
                function getSearchRestAPI(text)
                {
                    var searchURL = urlHelper.getUrl("search", {
                        text: getNameFilterURLParams(window.encodeURIComponent(text))
                    });
                    return searchURL;
                }

                // Private methods
                function load(searchText) {
                    var searchText = searchText ? searchText : "";
                    latestSearch = searchText;
                    self.searchMessage(self.label.searchFor + "'" + searchText + "' ...");

                  //  var restAPI = getSearchRestAPI(searchText);
                    //TODO: AJAX
                    /*bdpApp.xhr("GET", restAPI, null,
                            {showLoading: true, showGlobalErrorDialog: false,
                                doneHandler: function (result) {
                                    computeSearchList(result);
                                    updateMessage();
                                },
                                failHandler: function (result) {
                                    updateMessage();
                                }
                            });*/
                    var arr = new Array();
                    arr[0] = {
                    		id : "0",
                    		FIELD_NAME : "test",
                    		DESCRIPTION_NAME : "I will hold a basketball game"
                    };
                    arr[1] = {
                    		id : "1",
                    		FIELD_NAME : "test",
                    		DESCRIPTION_NAME : "Spring Outing"
                    };
                    data = {
                    		response : {
                    			docs : arr
                    		}
                    };
                    computeSearchList(data);
                }

                function updateMessage()
                {
                    self.searchMessage(self.results().length === 0
                            ? self.label.noResultsFound + "'" + (latestSearch
                                    || "") + "'" : self.label.resultsFound(self.results().length));
                }

                function computeSearchList(data) {
                    data = data || {};
                    data.response = data.response || {};
                    data.response.docs = data.response.docs || [];

                    var items = [];
                    for (var i = 0; i < data.response.docs.length; i++) {
                        items.push(searchListItem(data.response.docs[i]));
                    }
                    self.results(items);
                }

                function init(jsonData, searchText) {

                   // self.searchSubscribe = ko.postbox.subscribe("RequestSearch", function (searchRequest) {
                        self.results([]);
                        load(searchText);
                   // });

                    if (jsonData) {
                        computeSearchList(jsonData);
                    } else {
                        searchText = searchText ? searchText : "";
                        self.searchValue(searchText);
                       /* ko.postbox.publish("RequestSearch", {text: searchText});
                        ko.observable().publishOn("RedirectInPage");*/
                    }
                }

            }

            // Defines all of the actions we may wish to apply to a search item
            var allActions = {
                edit: {action: "edit", label: 'edit', icon: "edit-icon"},
                delet: {action: "delete", label: 'delete', icon: "delete-icon"},
                publish: {action: "publish", label: 'publish', icon: "publish-icon"},
                join: {action: "join", label: 'join', icon: "join-icon"},
            };

            // Search items --------------------------------------------------

            /*
             *  Represents an item in the search list. This uses the functional
             *  inheritnce pattern.
             *  Not a lot of functionality yet, but this will change as we
             *  introduce custom widgets for each type.
             */
            function searchListItem(data) {

                var that = {};
                that.data = data;
                that.id = data.id;
                that.name = data.FIELD_NAME;
                that.description = data.DESCRIPTION_NAME || "";
                that.actions = ko.computed(function () {
                    return [allActions.edit, allActions.delet, allActions.publish, allActions.join];
                }, that);

                that.infocard = ko.observable();

                that.loadSummary = doLoadSummary;

                return that;

                function doLoadSummary() {
                	if (!that.infocard()) {
                        that.infocard(infoCard(that));
                    }
                }
            }

            // Info cards -----------------------------------------------------


            function infoCard(spec) {
                var that = {};

                that.properties = ko.observableArray();
                that.description = ko.observable();

                initProperties();

                return that;

                function initProperties() {
                	//TODO: url should be hard code
                    /*var propertiesQueryCall = bdpApp.constructRestQueryUrlById(spec.propertyQuery, {id: spec.id});
                    bdpApp.xhr("GET", propertiesQueryCall, null, {doneHandler: populateProperties});*/
                	var data = {
                			results : {
                				NAME : "test name",
                				STARTED_AT : "10:00",
                				ENDED_AT : "11:00",
                				DEADLINE : "9:00",
                				STATUS : "unpblished"
                			}
                	};
                	populateProperties(data);
                }

                function populateProperties(data) {
                    if (data && data.results) {
                        var props = [];
                        $.each(data.results, function (key, val) {
                            var displayName = getDisplayName(key);
                            var displayValue = getDisplayValue(key, val);
                            props.push({key: displayName, name: displayName, value: val, displayValue: displayValue});
                        });
                        that.properties(props);
                    }
                }

                function getDisplayName(key) {
                    return (PROPERTY_DEFS[key] && PROPERTY_DEFS[key].display) || key;
                }

                function getDisplayValue(key, value) {
                    if (PROPERTY_DEFS[key] && PROPERTY_DEFS[key].renderer) {
                        return PROPERTY_DEFS[key].renderer(value);
                    }
                    return value;
                }
            }

            //clean up codes on unload of model can be placed here
            SearchResultsViewModel.prototype.dispose = function () {
                this.searchSubscribe.dispose();
            };
            // Return the model
           // return {viewModel: SearchResultsViewModel};
            $(document).ready(function () {
          	  ko.applyBindings(new SearchResultsViewModel());
          });
        });
