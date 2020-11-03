package by.vasiliuk.project.controller.command;

import by.vasiliuk.project.controller.command.impl.DefaultCommand;

public class CommandProvider {

   public static Command getCommand(String commandStr) {
       Command command;
       try {

//           String[] lang = commandStr.split("&", 2);
//           commandStr = lang[0];
           //todo forDel
           CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
           command = commandType.getCommand();
       } catch (IllegalArgumentException e) {
           command = new DefaultCommand();
       }
       return command;
   }
}
