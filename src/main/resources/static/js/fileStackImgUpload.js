 "use strict";

 $(document).ready(() => {
     // click upload profile image to change profile image
     $("#upload").click((e) => {
         e.preventDefault();
         uploadProfileImage();
     });
 });

 const client = filestack.init(filestackapi);

 function uploadProfileImage() {
     const options = {
         accept: ["image/*"],
         maxFiles: 1,
         onUploadDone: (results) => {
             $("#profileImage").attr("src", results.filesUploaded[0].url); // display the new image on web page
             $("#profileImageUrl").val(results.filesUploaded[0].url); // save the new image url to a hidden input

             let profileImageObj = {
                 profileImageUrl: $(".profileImageUrl").val()
             }

             // do AJAX request to save profile image to db
             $.ajax({
                 url: "/profile/image",
                 type: "POST",
                 data: JSON.stringify(profileImageObj),
                 contentType: "application/json",
                 dataType: "json",
                 timeout: 600000,
                 success: (response) => {
                     console.log("image uploaded successfully!");
                     console.log(response.profileImageUrl);
                     $("#profileImg").val(response.profileImageUrl);
                 },
                 error: (error) => {
                     console.log("Error: ", error);
                     alert("No image uploaded!");
                 }
             });
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }


 // https://cdn.filestackcontent.com/6NBa6pjQKGy3OEkIH0J2

 // https://cdn.filestackcontent.com/zPduwMuWROCS01EDdRV1

 // https://cdn.filestackcontent.com/mlddTvwS1GFVI0STXIfa

 // https://cdn.filestackcontent.com/B3Da08QOR1mpSwEmTp8p

