 "use strict";

 $(document).ready(() => {
     // click upload profile image to change profile image
     $("#upload").click((e) => {
         e.preventDefault();
         uploadProfileImage();
     });

     $("#adminUpload").click(() => {
         // upload image to filestack and store in db
         adminUploadUserProfileImage();
     })
 });

 const client = filestack.init(fileStackApi);

 function uploadProfileImage() {
     const options = {
         accept: ["image/*"],
         maxFiles: 1,
         onUploadDone: (results) => {
             console.log(results.filesUploaded[0].url);

             $("#profileImage").attr("src", results.filesUploaded[0].url); // display the new image on web page
             $("#profileImageUrl").val(results.filesUploaded[0].url); // save the new image url to a hidden input
             $("#profileImg").val(results.filesUploaded[0].url);  // save the new image url to a hidden input in /profile/settings form

             let profileImageObj = {
                 profileImageUrl: $("#profileImageUrl").val()
             }

             console.log(profileImageObj);

             // do AJAX request to save profile image to db
             $.ajax({
                 url: "/profile/image",
                 type: "POST",
                 data: JSON.stringify(profileImageObj),
                 contentType: "application/json; charset=UTF-8",
                 dataType: "json",
                 timeout: 600000,
                 success: (response) => {
                     console.log("image uploaded successfully!");
                     console.log(response);
                     console.log(response.profileImageUrl);
                     $("#profileImg").val(response.profileImageUrl);
                 },
                 error: (error) => {
                     console.log("Error: ", error);
                     // alert("No image uploaded!");
                 }
             });
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }


 function uploadImages() {
     const options = {
         accept: ["image/*"],
         maxFiles: 6,
         onUploadDone: (results) => {
             console.log(results);
             let images = "";
             for (let image of results.filesUploaded) {
                 images = images + image.url + " ";
             }
             $("#hidden").val(images.substring(0, images.length - 1));
             $(".fa-check").toggleClass("hidden");
         },
         onFileUploadFailed: (response) => {
             console.log(response);
             wiindow.location = "/error";
         }
     }

     client.picker(options).open();
 }


 function adminUploadUserProfileImage() {
     const options = {
         accept: ["image/*"],
         maxFiles: 1,
         onUploadDone: (results) => {
             console.log(results.filesUploaded[0].url);
             $("#profileImage").attr("src", results.filesUploaded[0].url); // display the new image on web page
             $("#profileImgUploadedAdmin").val(results.filesUploaded[0].url);  // save the new image url to a hidden input in /profile/{id}/edit form
         },
         onFileUploadFailed: (response) => {
             console.log(response);
         }
     }

     client.picker(options).open();
 }

 // https://cdn.filestackcontent.com/6NBa6pjQKGy3OEkIH0J2

 // https://cdn.filestackcontent.com/zPduwMuWROCS01EDdRV1

 //

 // https://cdn.filestackcontent.com/B3Da08QOR1mpSwEmTp8p



