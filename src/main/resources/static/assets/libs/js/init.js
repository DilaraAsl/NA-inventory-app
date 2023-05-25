(function($){
  $(function(){

    $('.sidenav').sidenav();
    $('.parallax').parallax();
    $('.dropdown-trigger').dropdown({coverTrigger: false, constrainWidth: false});
    $('.collapsible').collapsible();
    $('.modal').modal();
    $('select').formSelect();

  }); // end of document ready
})(jQuery); // end of jQuery name space

// function goBack() {
//   const defaultDeviceSearchURL = '/device/search'; // Replace with your file location or endpoint
//   const previousURL = document.referrer;
//
//   if (previousURL.includes('/device-search')||previousURL.includes('/assignee-search')) {
//     window.location.href = '/welcome'; // Replace with the desired home page URL
//   } else {
//     if (window.history.length > 1) {
//       window.history.back(); // Go back one step in history
//     } else {
//       window.location.href = defaultDeviceSearchURL; // Redirect to the default device search page
//     }
//
//     setTimeout(function() {
//       window.history.replaceState({}, '', defaultDeviceSearchURL); // Replace current state with the default device search URL state
//       location.reload(); // Reload the previous page
//     }, 100); // Add a slight delay before reloading
//   }
// }

function goBack() {
  const defaultDeviceSearchURL = '/device/search'; // Replace with your file location or endpoint
  const previousURL = document.referrer;

  if (previousURL.includes('/device-search') || previousURL.includes('/assignee-search')) {
    // If the previous URL is device search or assignee search, go back to the home page
    window.location.href = '/dashboard'; // Replace with the desired home page URL
  } else {
    // Otherwise, go back one step in history or redirect to default device search page
    if (window.history.length > 1) {
      window.history.back(); // Go back one step in history
      setTimeout(function() {
        window.location.replace(window.location.href); // Replace current page with the same page, triggering a reload
      }, 100); // Add a slight delay before reloading
    } else {
      window.location.href = defaultDeviceSearchURL; // Redirect to the default device search page
    }
  }
}

