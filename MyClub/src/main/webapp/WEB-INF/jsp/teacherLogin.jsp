
<%@ page language="java" import="java.util.*,com.clemson.model.*"
	pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Club</title>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="css/libs/oj/v3.0.0/alta/oj-alta-min.css" type="text/css"/>
    
    <script data-main="js/index" src="js/libs/require/require.js"></script>
    <style>
	#activityResults {
		margin-left: 70px;
		margin-right: 70px;
		margin-bottom: 20px;
		margin-top:20px;
		}
		
	.header {
		margin-left: 20px;
		margin-top:20px;
		margin-right: 20px;
		}
	</style>
</head>

<div class="oj-flex oj-sm-flex-wrap-nowrap oj-sm-justify-content-space-between header">
	<div id="SearchPanel" class="oj-flex-item">
	    <div class="oj-form" >
	        <form data-bind="submit: doSearch">
	             <div>
	                 	<input id="searchString" type="text" 
	                 	data-bind="attr: { placeholder: message.hint}, 
	                 	ojComponent: {
	                 		component: 'ojInputSearch', 
	                 		value: searchValue,
	                 		rootAttributes: {style:'min-width: 30em; max-width:30em;'}
	                 	}"/>     
	             </div>
	        </form>
	    </div>    
	</div>
	<div class="oj-flex-item ">
		 <button class="mainButton" data-bind="click: doCreate, ojComponent: { component: 'ojButton', label:label.create }">
		 </button>
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
                        click:$parent.handleMenuClick"></button>
                    <ul style="display:none"
                        data-bind="attr : {id: id, data: JSON.stringify(data)}, 
                            ojComponent: {component: 'ojMenu', select: $parent.handleMenuOption }">
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
    <h2 class="oj-header-border"></h2> 
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

<div style="display:none" class="editDialog" data-bind="attr: { id: 'editDialog'}, ojComponent: {component: 'ojDialog', rootAttributes: { style: 'width: 1000px'}}">                
  	<div style="padding: 10px" id="EditForm" >
       <form data-bind="submit: saveActivity">
       	<div data-bind="template: {name: 'newActivity-template', data: mode = 'edit'}"></div>
       </form>
    </div>
</div>

<template id="newActivity-template">
<div id="newActivityForm">
        <div class="oj-form-layout">
          <div class="oj-form oj-sm-odd-cols-12 oj-md-odd-cols-4 oj-md-labels-inline">   
                     
            <div class="oj-flex"> 
              <div class="oj-flex-item">
                <label for="text-input1" data-bind="text: $parent.label.name"></label>
              </div>
              <div class="oj-flex-item">
                <input id="text-input1" class="schedule-input-text" type="text" data-bind="ojComponent: {component: 'ojInputText',
                                            rootAttributes: {style:'width: 210px;'},
                                            value:$parent.name , required:true}"/>
              </div>
            </div>
            <div class="oj-flex"> 
              <div class="oj-flex-item">
                <label for="text-input2" data-bind="text: $parent.label.description"></label>
              </div>
              <div class="oj-flex-item">
                <textarea id="text-input2" class="schedule-input-text" 
                          data-bind="ojComponent: {component: 'ojTextArea',
                                                   rootAttributes: {style:'width: 210px;'},
                                                   value:$parent.description , required: true}"></textarea>
              </div>
            </div>
            
            <h2 class="oj-header-border"></h2>
            <div class="oj-flex"> 
              <div class="oj-flex-item">
                <label for="startDateTime" data-bind="text: $parent.label.startDate"></label>
              </div>
              <div class="oj-flex-item">
                <input id="startDateTime" data-bind="ojComponent: {component: 'ojInputDateTime',
                                    value:$parent.startTime , required:true, validators: [$parent.validateStartTime()]}"/> 
              </div>
            </div>
            <div class="oj-flex"> 
              <div class="oj-flex-item">
                <label for="endDateTime" data-bind="text: $parent.label.endDate"></label>
              </div>
              <div class="oj-flex-item">
                <input id="endDateTime" data-bind="ojComponent: {component: 'ojInputDateTime', value:$parent.endTime, 
                       required:true, validators: [$parent.validateEndTime()]}"/>
              </div>
            </div>
          </div>
        </div>
        <div id="newScheduleActions">
            <button type = "submit" class="mainButton" data-bind="ojComponent: { component: 'ojButton', label: $parent.label.save }"></button>
            <button id= "button" data-bind="click: $parent.cancelActivity, ojComponent: { component: 'ojButton', label: $parent.label.cancel }"></button>
        </div>
    </div>
    </template>        

</html>