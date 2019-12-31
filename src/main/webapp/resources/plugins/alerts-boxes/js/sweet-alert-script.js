

 $(document).ready(function(){

              $("#alert-basic").click(function(){
                  swal("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat, tincidunt vitae ipsum et, pellentesque maximus enim. Mauris eleifend ex semper, lobortis purus sed, pharetra felis");
              });

              $("#alert-title").click(function(){
                  swal("Here's the title!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat, tincidunt vitae ipsum et, pellentesque maximus enim. Mauris eleifend ex semper, lobortis purus sed, pharetra felis");
              });

              $("#alert-success").click(function(){
                  var msg=$(this).attr("data-msg")
                  swal("Good job!",msg, "success");
              });

              $("#alert-error").click(function(){
                  swal("Somthing Wrong!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat, tincidunt vitae ipsum et, pellentesque maximus enim. Mauris eleifend ex semper, lobortis purus sed, pharetra felis,", "error");
              });

              $("#alert-info").click(function(){
                  swal("Information!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat, tincidunt vitae ipsum et, pellentesque maximus enim. Mauris eleifend ex semper, lobortis purus sed, pharetra felis,", "info");
              });

              $("#alert-warning").click(function(){
                  swal("Warning!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat, tincidunt vitae ipsum et, pellentesque maximus enim. Mauris eleifend ex semper, lobortis purus sed, pharetra felis,", "warning");
              });

     // $("#confirm-btn-alert").click(function(){
     //     var id=$(this).attr("del-id");
     //     swal({
     //         title: "你确定吗？",
     //         text: "一旦删除，该指令无法撤销！",
     //         icon: "warning",
     //         buttons: true,
     //         dangerMode: true,
     //     })
     //         .then((willDelete) => {
     //             if (willDelete) {
     //                 deleteData(id);
     //                 swal("干得好! 该人物已被删除!", {
     //                     icon: "success",
     //                 });
     //             } else {
     //                 swal("该人物暂时安全。");
     //             }
     //         });

     });
