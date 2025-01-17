package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.IncludeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new IncludeCommand object.
 */
public class IncludeCommandParser implements Parser<IncludeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncludeCommand
     * and returns an IncludeCommand object for execution.
     * @param userInput String to be parsed
     * @return IncludeCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncludeCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_SOCIAL, PREFIX_LINK);
        Index index;
        String social = null;
        String link = null;

        if (!arePrefixesPresent(argMultimap, PREFIX_SOCIAL, PREFIX_LINK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            if (argMultimap.getValue(PREFIX_SOCIAL).isPresent()) {
                social = ParserUtil.parseSocial(argMultimap.getValue(PREFIX_SOCIAL).get());
            }

            if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
                link = ParserUtil.parseSocial(argMultimap.getValue(PREFIX_LINK).get());
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE), pe);
        }

        return new IncludeCommand(index, social, link);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
