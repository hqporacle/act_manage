<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Club</title>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="css/libs/oj/v3.0.0/alta/oj-alta-min.css" type="text/css"/>
    
    <script data-main="js/index" src="js/libs/require/require.js"></script>
</head>

<div id="SearchPanel">
    <div class="oj-form" >
        <form data-bind="submit: doSearch">
            <div class="oj-flex oj-sm-flex-wrap-nowrap">
                <button class="oj-flex-item oj-sm-flex-initial oj-button oj-component oj-enabled oj-button-half-chrome oj-button-icon-only oj-component-initnode oj-default" 
                	type="submit" id="buttonImage" 
                	data-bind="
                	ojComponent: {
	                    component:'ojButton', 
	                    chroming: 'half',
	                    display: 'icons', 
	                    icons: {start:'oj-fwk-icon oj-fwk-icon-magnifier'}, 
	                    label: 'Search'}" 
	                aria-labelledby="ui-id-9" title="Search">
                    <div class="oj-button-label">
	                    <span class="oj-button-icon oj-start oj-fwk-icon oj-fwk-icon-magnifier"></span>
	                    <span class="oj-button-text oj-helper-hidden-accessible" id="ui-id-9">Search</span>
                    </div>
                 </button>

                <div class="oj-flex-item">
                    <div>
                        <div>
                            <div class="oj-inputtext oj-form-control oj-component">
                            	<input id="searchString" type="text" 
                            	data-bind="attr: { placeholder: message.hint}, 
                            	ojComponent: {
                            		component: 'ojInputText', 
                            		value: searchString }" 
                            	placeholder="Search" 
                            	class="oj-inputtext-input oj-component-initnode"></div>        
                        </div>
                    </div>
                </div>  
            </div>
        </form>
    </div>    
</div>
    
<div id="activityResults" >
    <div data-bind="template: { name: 'search-result-template', foreach: resultsAllVisible }"></div>
    <!-- ko if: resultsAllVisible().length > 0 -->
        <span data-bind="text: label.showResults(resultsAllVisible().length, results().length)"></span>
       <!-- ko if: resultsAllVisible().length < results().length -->
            <span class="clickable" data-bind="text: label.showMore, click: function() { showMore();}"></span>
       <!-- /ko -->
    <!-- /ko -->
</div>
    
<template id="search-result-template"> 
    <div class="search-row">
        <div data-bind="ojComponent: {component: 'ojCollapsible', beforeExpand: loadSummary}">
            <div class="oj-flex oj-sm-flex-wrap-nowrap">
                <div class="oj-flex-item oj-sm-flex-initial">
                   <!--  <span class="dfmlListItemImage largeTypeIcon" data-bind="css: typeIconClass"/> -->
                </div>
                <div class="oj-flex-item searchContents">
                    <div class="oj-flex oj-flex-items-pad">
                        <div class="oj-flex-item oj-sm-8">
                            <div class="listItemValue" data-bind="text: description"></div>
                        </div>
                        <div class="oj-flex-item oj-sm-4 oj-sm-only-hide">
                            <!-- <div data-bind="text: deadline"></div> -->
                        </div>
                    </div>
                </div>
                
                <div class="oj-flex-item oj-sm-flex-initial actionMenu">
                    <button data-bind="ojComponent: {component: 'ojButton', label: 'Options',
                        menu: '#' + id}, 
                        click:$parents.handleMenuClick"></button>
                    <ul style="display:none"
                        data-bind="attr : {id: id, data: JSON.stringify(data)}, 
                            ojComponent: {component: 'ojMenu', select: $parents.handleMenuOption }">
                        <!-- ko foreach: actions -->
                            <li data-bind="attr: { 'data-action': action}">
                                <a href="#" data-bind="attr: { 'id': 'ui-id-' + action}" >
                                    <!-- <span class="oj-menu-item-icon" data-bind="css : icon"></span> -->
                                    <span data-bind="text: label"></span>
                                </a>
                            </li>
                        <!-- /ko -->
                    </ul>
                </div>
            </div>
        	<div>
	        	 <!-- ko if: infocard() -->                
	                        <div data-bind="template: { name: 'info-template', data: infocard }"></div>
	             <!-- /ko -->
            </div>
        </div>
    </div>
</template>

<template id="info-template">
    <div class="infoSummary">
        <div class="oj-sm-odd-cols-4 oj-sm-even-cols-8 oj-lg-odd-cols-2 oj-lg-even-cols-4 oj-sm-labels-inline">
            <h3 data-bind="text: $parents[1].label.infoSummaryProp"></h3>
            <div class="oj-flex" data-bind="foreach: $data.properties">
                <div class="oj-flex-item">
                  <label class="infoCardLabel" data-bind="attr: {for : key}, text: name"></label>
                </div>
                <div class="oj-flex-item">
                  <span data-bind="attr: { id : key }, text: displayValue"></span>
                </div>
            </div>
        </div>
    </div>
</template>
</html>