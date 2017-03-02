# Allergent

<b><u><i>Description</i></u></b>:<br/> 
An android app for checking whether a certain food product has allergens related to the user.

<b><u><i>How it works</i></u></b>:<br/> 
Our app uses OCR capability provided by Microsoft's Cognitive Services API to list all the ingredients of a food item from the contents sticker on its packet. Then, we check if the food items for specific allergens(using the Edamam api) that are inputted from the user the first time he logs in. Finally, we give the list of all ingredients and specify which, if any, may be dangerous to the user.

<b><u><i>How we built it</i></u></b>:<br/> 
We used Android Studio to make the app. The app requires the user to take a picture of the contents, we send this picture to Cognitive Services API as a bitmap to get the string of the contents. The string is then parsed and processed to determine the exact list of contents. This list is then passed to the Edamam API which of them contain allergens. We finally look at the allergens that affect the user, compare it with the output from Edamam and accordingly educate the user on the risks of consuming the product in question.

<b><u><i>Screenshots</i></u></b>:<br/> 
