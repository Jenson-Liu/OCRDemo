sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel"
], function(Controller, JSONModel) {
    "use strict";

    return Controller.extend("Camera.controller.Home", {

        onInit  : function() {
            this.getView().setModel( new JSONModel({
                photos: []
            }) );
        },


        onSnapshot: function (oEvent) {
            // The image is inside oEvent, on the image parameter,
            // let's grab it.
            var oModel = this.getView().getModel();
            var aPhotos = oModel.getProperty("/photos");
            aPhotos.push({src: oEvent.getParameter("image")});
            var file = oEvent.getParameter("image");
            console.log(oEvent.getParameter("image"));
            oModel.setProperty("/photos", aPhotos);
            oModel.refresh(true);
            var param = new FormData();
            param.append("file",file);
            $.ajax({
                url: "/innovation/importImg",//请求地址
                data: param,
                type: "post",//提交的方式
                cache: false,
                processData: false,
                contentType: false,
                success: function (data) {   // 这里的data就是json格式的数据
                    window.location.href="/innovation/edit";
                }
            });
        },

        /**
         * Stop the camera when the tab is not visible.
         * @param {object} name
         * @returns {object}
         */
        onTabSelect: function (oEvent) {
            var oTab = oEvent.getParameter("key");
            var oCamera = this.getView().byId("idCamera");
            if (oTab !== "demo") {
                oCamera.stopCamera();
            } else {
                oCamera.rerender();
            }
        }


    });
});