package by.vasiliuk.project.controller.command;

import by.vasiliuk.project.controller.command.impl.*;

public enum CommandType {
    ALL_ADVERTS(new AllAdvertsCommand()),
    ALL_USERS(new AllUsersCommand()),
    EDIT_USER_PROFILE(new EditUserProfileCommand()),
    EDIT_ADVERT(new EditAdvertListCommand()),
    SAVE_EDITED_ADVERTS(new SaveEditedAdvertsCommand()),
    DELETE_USER(new DeleteUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LANGUAGE(new LanguageCommand()),
    NEW_ADVERT(new NewAdvertCommand()),
    ADVERTS_BY_SECTION_ID(new GetAdvertsBySectionIdCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTRATION(new RegisterUserCommand()),
    TO_CREATE_ADVERTISEMENT(new ToCreateAdvertisementCommand()),
    TO_REGISTER(new ToRegisterCommand()),
    TO_PROFILE(new ToProfileCommand()),
    TO_EDIT_USER(new ToEditUserCommand()),
    SECTIONS(new SectionsCommand()),
    GET_USER(new GetUserCommand()),
    EDIT_USER_COMMAND(new EditUserProfileCommand())
    ;
    CommandType(Command command) {
        this.command = command;
    }
    private Command command;

    public Command getCommand() {
        return command;
    }
}
