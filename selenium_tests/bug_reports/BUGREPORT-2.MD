**Title:** English used in Login page error is not correct.

**ID:** BUG-002

**Reported By:** William Walsh  
**Date:** 04/11/2024
**Environment:**
- **Operating System:** Common across
- **Browser:** Chrome 130.0.6723.92
- **Application Version:** v?.?.?

**Severity:** Low  
**Priority:** P4 (Negligible)

**Description:**  
When a user logs in without a password, the error text contains a string that is not correct. This issue occurs consistently across multiple browsers.

**Steps to Reproduce:**
1. Open the browser and navigate to the login page.(https://www.saucedemo.com/)
2. Enter a password.
3. Click on the "Login" button.
4. Observe the error message:
```
Epic sadface: Username is required
```
**Expected Result:**  
Upon not entering a username and clicking the login button, the error should use correct english.

**Actual Result:**  
The page displays the incorrect statement: "Epic sadface..."

**Attachments:**
- Screenshot of the incorrect english: ![img_3.png](img_3.png)

**Workaround (if any):**  
None.