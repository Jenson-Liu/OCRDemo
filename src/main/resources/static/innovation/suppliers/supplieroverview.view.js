sap.ui.jsview("suppliers.supplieroverview", {

    /** Specifies the Controller belonging to this View.
     * In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
     * @memberOf suppliers.supplieroverview
     */
    getControllerName : function() {
        return "suppliers.supplieroverview";
    },

    /** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed.
     * Since the Controller is given to this method, its event handlers can be attached right away.
     * @memberOf suppliers.supplieroverview
     */
    createContent : function(oController) {
        // define columns and table
        var oColumns = [
            new sap.ui.table.Column({
                label: new sap.m.Label({text:"ID"}),
                template: new sap.m.Text().bindProperty("text", "ID"),
                sortProperty: "ID",
            }),
            new sap.ui.table.Column({
                label: new sap.m.Label({text:"Name"}),
                template: new sap.m.Text().bindProperty("text", "Name"),
                sortProperty: "ID",

            })
        ];

        var oTable = new sap.ui.table.Table({
            width: "100%",
            title: "供应商列表",
            visibleRowCount: 2,
            firstVisibleRow: 0,
            editable: false,
            selectionMode: sap.ui.table.SelectionMode.Single,
            columns: oColumns
        });

        oTable.bindRows("/Suppliers");

        var oPage = new sap.m.Page({
            title: "供应商",
            content: [oTable]
        });

        return oPage;
    }

});
