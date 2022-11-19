package UI.Languages;

public class EnglishUI implements LanguageInterface {

    @Override
    public String[] menuLabels() {
        String[] labels = { "Contacts", "Settings" };
        return labels;
    }

    @Override
    public String senderDescription() {
        return "You are talking to ";
    }

    @Override
    public String sendButtonLabel() {
        return "Send";
    }

    @Override
    public String setActive() {
        return "Are you connected";
    }

    @Override
    public String logOut() {
        return "Log Out";
    }

    @Override
    public String setContact() {
        return "Add contacts";
    }

    @Override
    public String informationNotice() {
        return "Information Notice";
    }

    @Override
    public String addContact() {
        return "Add contact";
    }

    @Override
    public String fieldEmpty() {
        return "The field is empty";
    }

    @Override
    public String enterUserName() {
        return "Enter the username";
    }

    @Override
    public String userNameDontExit() {
        return "Username does not exist";
    }

    @Override
    public String userHasAdded() {
        return "The user has already been added";
    }

    @Override
    public String cannotAddYourself() {
        return "You cannot add yourself";
    }

}
