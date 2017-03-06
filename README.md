# Efficient-Bank
Final project in Data Structures and Algorithms course. Mainly demonstrates use of red-black trees and some OOP principles.
<div dir="rtl">

* מספר לקוח\חשבון =  .accountId מספר זהות =  .customerId  

מערכת הבנק מורכבת מהמחלקות הבאות:  
Customer  - מחלקה המייצגת לקוח בבנק ומכילה את תכונות הלקוח -  
שם -name 
מספר תעודת זהות -CustomerId  
מספר לקוח -AccountId  
יתרה -balance  
RBTree  - מחלקה אבסטרקטית המייצגת עץ אדום שחור ומכילה תכונת root המצביעה לראש העץ ותכונת nil סטטית המוסכמת בעצים אדומים שחורים. בנוסף מכילה פעולות המשותפות לשני העצים balanceTree ו AccountIdTree והןDelete , DeleteFixup, LeftRoate, RightRotate, InsertFixup. כמו כן, פעולות כלליות יותר -TreeMinimum, TreeMaximum, TreeSuccessor.  
BalanceTree  - יורשת מ RBTree ומייצגת עץ אדום שחור המנהל את הלקוחות ע"פ יתרה. מכילה פעולות ספציפיות לעץ זה - הכנסה של איבר (ע"פ יתרה).  
AccountIdTree  - יורשת מ RBTree ומייצגת עץ אדום שחור המנהל את הלקוחות ע"פ מספר חשבון (מס לקוח). מכילה פעולות ספציפיות לעץ זה - הכנסה של איבר (ע"פ מספר חשבון), חיפוש איבר (ע"פ מספר חשבון).  
Bank  - מחלקה המייצגת את הבנק שלנו. מכילה כתכונות את שני העצים -balanceTree, accountIdTree. כמו כן מכילה את פעולות השאילתות וההודעות המפורטות במטלה - עידכון יתרה, הוספת לקוח חדש, מחיקת לקוח, בדיקת יתרה, שליפת הלקוח בעל היתרה הגבוהה ביותר, הדפסת כל הלקוחות בעלי יתרה שלילית.  
Node  - מייצג איבר בעץ אדום שחור. בעל התכונות המוסכמותcolor, left, right, p. בנוסף מכיל את התכונות customer המצביע לאובייקט הלקוח שלו ו parallel המצביע ל node המתאים לו (ה node המכיל את אותו אובייקט לקוח) בעץ השני.  
Main - המחלקה בעלת הפעולה הסטטית המשמשת לתחילת הרצת הקוד.  
פעולות שינוי ושאילתות:  
UpdateBalanceByAccountId - עדכון יתרה של לקוח ע"פ מספר החשבון(לקוח).  
השגרה מקבלת את הסכום שיש להוסיף ליתרה (יכול להיות חיובי או שלילי) ואת מספר החשבון שאת יתרתו מעדכנים. נחפש את הלקוח בעץ accountIdTree ונפנה אל ה parallel שלו - זהו איבר הלקוח בעץbalanceTree . נעדכן את היתרה של אובייקט הלקוח (נשמור בידינו אובייקט זה). כעת עץ היתרות עלול להפר את התכונות של עץ חיפוש בינארי. נמחק כל איבר מהעץ שלו וניצור 2 node-ים חדשים וריקים בעלי ה customer ששמרנו קודם. כעת נדאג שכל parallel יצביע ל node המתאים - כלומר ה node-ים יצביעו "אחד לשני" (כל parallel מצביע ל node הנגדי). כל שנותר הוא להוסיף את ה node-ים לעץ ע"י insert.  
•	חיפוש איבר בעץ, מחיקת איבר והוספת איבר הן פעולות בעלות זמן ריצה O(lg n) ולכן זמן הריצה של שגרה זו הוא (O(lg n.  
 - AddNewCustomer הוספת לקוח חדש.  
השגרה מקבלת את אובייקט הלקוח שיש להוסיף. ניצור 2 אובייקטים של node חדשים וריקים לחלוטין חוץ מתכונת customer שתצביע לאובייקט הלקוח שקיבלנו. נעדכן את תכונת ה parallel של כל אחד מהם כך שכל parallel יצביע ל node ה"נגדי" - ה parllel של node של העץ balanceTree יצביע ל node של העץ accountIdTree וההיפך. נכניס את המתאים לעץ balanceTree ואת השני לעץaccountIdTree .  
•	הוספת כל אחד מה node-ים לעצים המתאימים - O(lgn) ולכן זהו זמן ריצת השגרה כולה.  
 - RemoveCustomerByAccountId מחיקת לקוח ע"פ מספר חשבון.  
השגרה מקבלת את מספר החשבון של הלקוח העוזב. נחפש את ה node של הלקוח בעץ accountIdTree ונבדוק אם יתרתו אפס. אם כן - נוכל להמשיך במחיקה. אם לא - נדאג שהלקוח יאפס את יתרתו. נקרא לשגרה updateBalanceByAccountId ונשלח לה סכום כסף השווה למינוס היתרה הקיימת - שכן זהו הסכום שיש להוסיף על מנת לאפס את היתרה. כזכור, השגרה updateBalanceByAccountId מוחקת את הלקוחות מהעץ ומוסיפה אותם מחדש (עם-node ים חדשים בעלי כתובת חדשה). לכן נחפש שוב את ה node המתאים ע"י accountId. נשמור גם את ה parallel שלו (זהו ה node בעץ balanceTree) וכעת כששני ה node-ים בידינו נמחק כל אחד מהעץ שלו.  
•	חיפוש איבר בעץ ומחיקה של איבר מעץ - כל אחד בעל סיבוכויות O(lg n) וכל אחד קורה מספר קבוע של פעמים. לכן (O(lgn זהו זמן הריצה של השגרה כולה.  

PrintBalancebyAccountId  - הדפסת יתרה של לקוח ע"פ מספר חשבון.  
השגרה מקבלת מספר חשבון של לקוח. נחפש את הלקוח בעץ accountId ע"פ מספר החשבון ואז נפנה לתכונת ה customer שלו - אובייקט הלקוח - ואל תכונת ה balance שלו. נדפיס את היתרה.  
•	חיפוש בעץ - ( O(lgnולכן זהו זמן הריצה של השגרה כולה.  
PrintMaxBalanceCustomer  - הדפסת פרטי הלקוח בעל היתרה הגבוהה ביותר.  
נחפש את הלקוח בעל היתרה הגבוהה ביותר בעץ היתרות ע"י השגרה הקיימתTreeMaximum  (העץ הוא עץ חיפוש בינארי ולכן הלקוח הנדרש יהיה העלה הימני ביותר.) נדפיס את היתרה. 
•	זמן ריצת השגרה TreeMinimum הוא O(lgn) ולכן זהו זמן ריצת השגרה כולה.  
PrintNegativeBalanceCustomers  - הדפסת כל הלקוחות בעלי יתרה שלילית.  
נבדוק שהעץ לא ריק ואז נקרא לשגרה printNeg הרקורסיבית - זוהי שגרת עזר שפועלת בדומה לסריקתinOrder . כידוע, סריקה תוכית של עץ חיפוש בינארי תיתן רשימה של ערכי העץ בסדר עולה. לכן בכל שלב בסריקה נבדוק אם ערך האיבר שלילי. אם כן - נדפיס את פרטי הלקוח של איבר זה. ונמשיך בסריקה לעבר תת העץ הימני. אחרת - האיבר הוא חיובי ולכן לא נטרח להמשיך בסריקה ולא נפנה לתת העץ הימני.  
•	מתבצעת סריקה תוכית של כל האיברים בעץ (במקרה הגרוע) ולכן זמן הריצה הוא ( O(n.  
</div>