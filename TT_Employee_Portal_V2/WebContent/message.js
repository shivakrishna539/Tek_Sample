
/**
 * 
 */
function OnButton1()
{
    document.Form1.action = "UpdateAddress"    // First target
    document.Form1.target = "iframe1";    // Open in a iframe
    document.Form1.submit();        // Submit the page
    document.Form1.action = "EditDeployment"    // Second target
    document.Form1.target = "iframe2";    // Open in a iframe
    document.Form1.submit();        // Submit the page
    return true;
}