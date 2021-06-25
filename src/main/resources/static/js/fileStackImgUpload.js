 "use strict";

 $(document).ready(() => {
     // click upload profile image
     $("#upload").click((e) => {
         e.preventDefault();
         uploadProfileImage();
     });

     let profileImageObj = {
         profile_image_url: $("#profileImage").attr("src")
     }
     fetch("/profile/image", {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify(profileImageObj)
     }).then( response => {
         console.log(response);
     }).catch( error => {
         console.log(error);
     });
 });

 const client = filestack.init(filestackapi);
 console.log(filestackapi);

 function uploadProfileImage() {
     const options = {
         // accept: ["image/*"],
         // maxFiles: 1,
         onUploadDone: (results) => {
             console.log(results.filesUploaded[0].url);
             $("#profileImage").attr("src", results.filesUploaded[0].url);
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }