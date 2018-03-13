// RESFUL UI.
function RESTfulUi( uiContainer, logContainer, entitiy ) {
    // Ui container.
    this.uiContainer = uiContainer;
    // Structure of Entities.
    this.entitiy = entitiy;

    RESTfulUi.prototype.uiTable = function( arrayObjs ) {
        if ( !Array.isArray( arrayObjs ) ) return;
        var table = document.createElement( "table" );
        var thead = document.createElement( "thead" );
        var tbody = document.createElement( "tbody" );
        var i = 0;
        var obj = arrayObjs[ i ];
        var trh = document.createElement( "tr" );
        for ( var property in obj ) {
            var th = document.createElement( "th" );
            th.appendChild( document.createTextNode( property ) );
            trh.appendChild( th );
        }
        thead.appendChild( trh );
        while ( i < arrayObjs.length ) {
            var tr = document.createElement( "tr" );
            for ( var property in obj ) {
                var td = document.createElement( "td" );
                td.appendChild( document.createTextNode( obj[ property ] ) );
                tr.appendChild( td );
            }
            tbody.appendChild( tr );
            i++;
        }
        table.className = "ui-table";
        table.appendChild( thead );
        table.appendChild( tbody );
        return table;
    };

    RESTfulUi.prototype.uiPannel = function( ) {
        if ( !this.entitiy || !this.entitiy.hasOwnProperty( "pannelOptions" ) ) return;
        var div = document.createElement( "div" );
        var ul = document.createElement( "ul" );
        var me = this;
        for ( var property in this.entitiy.pannelOptions ) {
            var option = document.createElement( "li" );
            option.appendChild( document.createTextNode( property ) );
            $( option ).click( function( ev ) {
                try {
                    ul.getElementsByClassName( "ui-pannel-option-selected" )[ 0 ].className = "";
                } catch ( e ) { }
                 ev.target.className = "ui-pannel-option-selected";
            } );
            // CRUD Operations.
            ( function( url, method, contentType, data, dataType ) {
                switch ( method.toUpperCase( ) ) {
                    case "GET":
                        $( option ).click( function( ) {
                            $( ".ui-table" ).remove( );
                            try {
                                me.request(
                                    url, method, contentType, data, dataType,
                                    function( request ) {
                                        if ( Array.isArray( request ) )
                                             me.uiContainer.appendChild( me.uiTable( request ) );
                                        else me.uiContainer.appendChild( me.uiTable( [ ].push( request ) ) );
                                } );
                            } catch( e ) { console.dir( e ); }
                        } );
                    break;
                    case "POST":
                    break;
                    case "PUT":
                    break;
                    case "DELETE":
                    break;
                }
            } )(
                me.entitiy.pannelOptions[ property ].url,
                me.entitiy.pannelOptions[ property ].method,
                me.entitiy.pannelOptions[ property ].contentType,
                me.entitiy.pannelOptions[ property ].data,
                me.entitiy.pannelOptions[ property ].dataType );
            ul.appendChild( option );
        }
        div.id = "ui-pannel";
        div.appendChild( ul );
        return div;
    };

    RESTfulUi.prototype.createUi = function( ) {
        if ( this.cleanUi( ) ) {
            var element = this.uiPannel( );
            if ( element )
                this.uiContainer.appendChild( element );
            else alert( "Not Implemented!" );
        }
    };

    RESTfulUi.prototype.cleanUi = function( ) {
        while ( this.uiContainer.firstChild )
            this.uiContainer.removeChild( this.uiContainer.firstChild );
        if ( this.logContainer ) while ( this.logContainer.firstChild )
            this.logContainer.removeChild( this.logContainer.firstChild );
        return true;
    };

    RESTfulUi.prototype.request = function( url, method, contentType, data, dataType, callback ) {
        var li = document.createElement( "li" );
        var span = document.createElement( "span" );
        span.style[ "text-decoration" ] = "underline";
        span.className = "ui-console-request";
        span.appendChild( document.createTextNode( "REQUEST: " ) );
        li.appendChild( span );
        li.appendChild( document.createElement( "br" ) );
        li.appendChild( document.createTextNode( "method: " + method ) );
        li.appendChild( document.createElement( "br" ) );
        li.appendChild( document.createTextNode( "contentType: " + contentType ) );
        li.appendChild( document.createElement( "br" ) );
        li.appendChild( document.createTextNode( "data: " + ( data ? data.toString( ) : "empty" ) ) );
        li.appendChild( document.createElement( "br" ) );
        li.appendChild( document.createTextNode( "dataType: " + dataType ) );
        logContainer.appendChild( li );
        $.ajax( {
             url: url, type: method, contentType: contentType, data: data, dataType: dataType,
             success: function( response ) {
                var li = document.createElement( "li" );
                var span = document.createElement( "span" );
                span.style[ "text-decoration" ] = "underline";
                span.className = "ui-console-response-ok";
                span.appendChild( document.createTextNode( "RESPONSE: " ) );
                li.appendChild( span );
                li.appendChild( document.createElement( "br" ) );
                li.appendChild( document.createTextNode( response.toString( ) ) );
                logContainer.appendChild( li );
                logContainer.appendChild( document.createElement( "li" ) );
                callback( response );
             }
        } );
    };
}
// ON DOCUMENT READY EVENT.
$( document ).ready( function( ) {
    var entities ={
        enterprises: {
            pannelOptions: {
                getAll: {
                    method: "GET",
                    url: "http://localhost:8080/enterprises",
                    contentType: "aplication/json",
                    dataType: "json"
                }, get: {
                    method: "GET",
                    url: "http://localhost:8080/enterprises/"
                    form: {
                        id: {
                            dataType: "string",
                            inputType: "text",
                            optional: false
                        }
                    }
                }, post: {
                    method: "POST",
                    url: "http://localhost:8080/enterprises",
                    form: {
                        name: {
                            dataType: "string",
                            inputType: "text",
                            optional: false
                        }
                    }
                }
            }
        }
    };
    var uiContainer = document.getElementById( "content" );
    var logContainer = document.getElementById( "output-text" );
    var navElements = $( "#nav > li" );
    navElements.click( function( e ) {
        e.target.className = "selected";
        navElements.each( function ( ) {
            if ( this != e.target )
                this.className = "";
        } );
        restfulUi = new RESTfulUi( uiContainer, logContainer, entities[ e.target.id ] ).createUi( );
    } );
} );