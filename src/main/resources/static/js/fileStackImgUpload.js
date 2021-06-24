 "use strict";

 const client = filestack.init(filestackapi);
 const options = {
     accept: ["image/*"],
     maxFiles: 1,
     onUploadDone:
     function (results){
         console.log(results.filesUploaded[0].url);
         $("#profileImageUrl").val(results.filesUploaded[0].url);
     }
 }

 $("#upload").click(() => {
     client.picker(options).open();
 });