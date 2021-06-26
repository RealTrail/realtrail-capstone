 "use strict";

 $(document).ready(() => {
     let isUploaded = false;

     if (isUploaded === false) { // user doesn't want to change profile image
         $(".profileImageUrl").val($("#originalImage").val());
     }

     // click upload profile image to change profile image
     $("#upload").click((e) => {
         e.preventDefault();
         fireAjaxSubmit(isUploaded);

         // fetch("/profile/image", {
         //     method: 'POST',
         //     headers: {
         //         'Content-Type': 'application/json'
         //     },
         //     body: JSON.stringify(profileImageObj)
         // }).then( (response) => {
         //     console.log(response);
         //
         // }).catch( (error) => {
         //     console.log("Error: ", error);
         //     alert("Oops, something went wrong, cannot change profile image for now. Please try again later");
         // });  // fetch is not working? why?
     });


 });

 const client = filestack.init(filestackapi);

 function uploadProfileImage() {
     const options = {
         accept: ["image/*"],
         maxFiles: 1,
         onUploadDone: (results) => {
             $("#profileImage").attr("src", results.filesUploaded[0].url);
             $(".profileImageUrl").val(results.filesUploaded[0].url);
             console.log($(".profileImageUrl").val());
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }

 function fireAjaxSubmit(isUploaded) {
     uploadProfileImage();
     isUploaded = true;
     let profileImageObj = {
         profile_image_url: $(".profileImageUrl").val()
     }

     $(".profileImageUrl").change(() => {
         $.ajax({
             url: "/profile/image",
             type: "POST",
             data: profileImageObj,
             processData: false, //prevent jQuery from automatically transforming the data into a query string
             contentType: false,
             cache: false,
             timeout: 600000,
             success: (response) => {
                 console.log(response);
             },
             error: (error) => {
                 console.log("Error: ", error);
                 alert("Oops, something went wrong, cannot change profile image for now. Please try again later");
             }
         });
     });
 }

 // https://cdn.filestackcontent.com/6NBa6pjQKGy3OEkIH0J2

 // https://cdn.filestackcontent.com/zPduwMuWROCS01EDdRV1