# Feature Toggling 

After a successful Deposit or Withdraw we want to send  a message to the owner of account. We can send message using Email or Text providers. 

Currently, the email and text functionality are not released, and the toggles are done with simple commenting out.

This means we can't toggle dynamically between those features.

We're going to refactor this code in 4 steps to make it a more flexible and maintainable.

steps:
* _Step 1_ change commented out toggles into if-else conditions. ([Diff](https://github.com/mirsaeedi/SoftwareTestingTurorials/compare/4c73143a4d9f410b0bcd9202a4922181415e7db1..913735e95517be7d68f43461ed86b4f0941de02e]))
  1. Apparantly, we need a better way of toggling.
* _Step 2_ use inversion of control for the toggle flags ([Diff](https://github.com/mirsaeedi/SoftwareTestingTurorials/compare/913735e95517be7d68f43461ed86b4f0941de02e..1e82cc021a60fe0691544f3931848d83a8a467b5]))
  1. CoreService is not testable.
  2. The consumer of CoreService has no way to change its behavior.
  3. CoreService should not be responsible for making decisions about the way we want to send messages and toggle features.
* _Step 3_ create a factory and use strategy pattern to inject the toggle flags ([Diff](https://github.com/mirsaeedi/SoftwareTestingTurorials/compare/1e82cc021a60fe0691544f3931848d83a8a467b5..75c7329ff6fc4f209099a28e268332a52fc4eeb5]))
  1. Toggling logic is scattered over code (code duplication)
  2. Toggling logic and toggling point are coupled
  3. Classes that use features need to be aware of toggling logic and all features.
  4. If-Else conditions make the code messy, unreadable, and unmaintainable.
* _Step 4_ read the toggle flags from a config file in the factory class ([Diff](https://github.com/mirsaeedi/SoftwareTestingTurorials/compare/75c7329ff6fc4f209099a28e268332a52fc4eeb5..0efbf24cf7c7bd185cc2cbc2a4750678910d3f04]))
  1. We cannot toggle dynamically.
  2. CoreService is coupled to BaseMessagingFeature. What if we want to add other features? we need to change its signature.
  3. What if we want to activate all features at the same time? what if none of the features are active?

# Fetch the bits

```

git clone https://github.com/mirsaeedi/SoftwareTestingTurorials
git pull origin feature_toggling
git checkout feature_toggling

```


