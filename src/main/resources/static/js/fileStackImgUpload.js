 "use strict";

 $(document).ready(() => {
     // click upload profile image
     $("#upload").click((e) => {
         e.preventDefault();
         uploadProfileImage();
         let profileImageObj = {
             profile_image_url: $("#profileImageUrl").val()
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
             console.log("Error: ", error);
             alert("Oops, something went wrong, cannot change profile image for now. Please try again later");
         });
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
             $("#profileImageUrl").val(results.filesUploaded[0].url);
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }