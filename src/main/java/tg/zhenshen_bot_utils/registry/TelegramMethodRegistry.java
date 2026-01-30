package tg.zhenshen_bot_utils.registry;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.adminrights.*;
import org.telegram.telegrambots.meta.api.methods.commands.*;
import org.telegram.telegrambots.meta.api.methods.description.*;
import org.telegram.telegrambots.meta.api.methods.forum.*;
import org.telegram.telegrambots.meta.api.methods.games.*;
import org.telegram.telegrambots.meta.api.methods.groupadministration.*;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.menubutton.*;
import org.telegram.telegrambots.meta.api.methods.name.GetMyName;
import org.telegram.telegrambots.meta.api.methods.name.SetMyName;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.*;
import org.telegram.telegrambots.meta.api.methods.polls.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.stickers.*;
import org.telegram.telegrambots.meta.api.methods.updates.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.methods.webapp.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TelegramMethodRegistry {

    private static final Map<Class<?>, String> METHOD_NAMES = new ConcurrentHashMap<>();

    static {
        register(SendMessage.class, "sendMessage");
        register(SendPhoto.class, "sendPhoto");
        register(SendVideo.class, "sendVideo");
        register(SendAnimation.class, "sendAnimation");
        register(SendAudio.class, "sendAudio");
        register(SendDocument.class, "sendDocument");
        register(SendSticker.class, "sendSticker");
        register(SendVoice.class, "sendVoice");
        register(SendVideoNote.class, "sendVideoNote");
        register(SendMediaGroup.class, "sendMediaGroup");
        register(SendLocation.class, "sendLocation");
        register(SendVenue.class, "sendVenue");
        register(SendContact.class, "sendContact");
        register(SendDice.class, "sendDice");
        register(SendPoll.class, "sendPoll");
        register(SendGame.class, "sendGame");
        register(SendInvoice.class, "sendInvoice");
        register(ForwardMessage.class, "forwardMessage");
        register(ForwardMessages.class, "forwardMessages");
        register(CopyMessage.class, "copyMessage");
        register(CopyMessages.class, "copyMessages");

        register(EditMessageText.class, "editMessageText");
        register(EditMessageCaption.class, "editMessageCaption");
        register(EditMessageMedia.class, "editMessageMedia");
        register(EditMessageReplyMarkup.class, "editMessageReplyMarkup");
        register(EditMessageLiveLocation.class, "editMessageLiveLocation");
        register(StopMessageLiveLocation.class, "stopMessageLiveLocation");
        register(DeleteMessage.class, "deleteMessage");
        register(DeleteMessages.class, "deleteMessages");

        register(AnswerCallbackQuery.class, "answerCallbackQuery");

        register(GetChat.class, "getChat");
        register(GetChatAdministrators.class, "getChatAdministrators");
        register(GetChatMember.class, "getChatMember");
        register(GetChatMemberCount.class, "getChatMemberCount");
        register(LeaveChat.class, "leaveChat");
        register(BanChatMember.class, "banChatMember");
        register(UnbanChatMember.class, "unbanChatMember");
        register(RestrictChatMember.class, "restrictChatMember");
        register(PromoteChatMember.class, "promoteChatMember");
        register(SetChatAdministratorCustomTitle.class, "setChatAdministratorCustomTitle");
        register(BanChatSenderChat.class, "banChatSenderChat");
        register(UnbanChatSenderChat.class, "unbanChatSenderChat");
        register(SetChatPermissions.class, "setChatPermissions");
        register(ExportChatInviteLink.class, "exportChatInviteLink");
        register(CreateChatInviteLink.class, "createChatInviteLink");
        register(EditChatInviteLink.class, "editChatInviteLink");
        register(RevokeChatInviteLink.class, "revokeChatInviteLink");
        register(ApproveChatJoinRequest.class, "approveChatJoinRequest");
        register(DeclineChatJoinRequest.class, "declineChatJoinRequest");
        register(SetChatPhoto.class, "setChatPhoto");
        register(DeleteChatPhoto.class, "deleteChatPhoto");
        register(SetChatTitle.class, "setChatTitle");
        register(SetChatDescription.class, "setChatDescription");
        register(PinChatMessage.class, "pinChatMessage");
        register(UnpinChatMessage.class, "unpinChatMessage");
        register(UnpinAllChatMessages.class, "unpinAllChatMessages");

        // --- Forum ---
        register(CreateForumTopic.class, "createForumTopic");
        register(EditForumTopic.class, "editForumTopic");
        register(CloseForumTopic.class, "closeForumTopic");
        register(ReopenForumTopic.class, "reopenForumTopic");
        register(DeleteForumTopic.class, "deleteForumTopic");
        register(UnpinAllForumTopicMessages.class, "unpinAllForumTopicMessages");
        register(GetForumTopicIconStickers.class, "getForumTopicIconStickers");
        register(EditGeneralForumTopic.class, "editGeneralForumTopic");
        register(CloseGeneralForumTopic.class, "closeGeneralForumTopic");
        register(ReopenGeneralForumTopic.class, "reopenGeneralForumTopic");
        register(HideGeneralForumTopic.class, "hideGeneralForumTopic");
        register(UnhideGeneralForumTopic.class, "unhideGeneralForumTopic");

        register(GetStickerSet.class, "getStickerSet");
        register(GetCustomEmojiStickers.class, "getCustomEmojiStickers");
        register(UploadStickerFile.class, "uploadStickerFile");
        register(CreateNewStickerSet.class, "createNewStickerSet");
        register(AddStickerToSet.class, "addStickerToSet");
        register(SetStickerPositionInSet.class, "setStickerPositionInSet");
        register(DeleteStickerFromSet.class, "deleteStickerFromSet");
        register(ReplaceStickerInSet.class, "replaceStickerInSet");
        register(SetStickerSetTitle.class, "setStickerSetTitle");
        register(SetStickerSetThumbnail.class, "setStickerSetThumbnail");
        register(SetCustomEmojiStickerSetThumbnail.class, "setCustomEmojiStickerSetThumbnail");
        register(DeleteStickerSet.class, "deleteStickerSet");

        register(SetMyCommands.class, "setMyCommands");
        register(DeleteMyCommands.class, "deleteMyCommands");
        register(GetMyCommands.class, "getMyCommands");
        register(SetMyName.class, "setMyName");
        register(GetMyName.class, "getMyName");
        register(SetMyDescription.class, "setMyDescription");
        register(GetMyDescription.class, "getMyDescription");
        register(SetMyShortDescription.class, "setMyShortDescription");
        register(GetMyShortDescription.class, "getMyShortDescription");
        register(SetChatMenuButton.class, "setChatMenuButton");
        register(GetChatMenuButton.class, "getChatMenuButton");
        register(SetMyDefaultAdministratorRights.class, "setMyDefaultAdministratorRights");
        register(GetMyDefaultAdministratorRights.class, "getMyDefaultAdministratorRights");

        register(AnswerInlineQuery.class, "answerInlineQuery");
        register(AnswerWebAppQuery.class, "answerWebAppQuery");
        register(AnswerShippingQuery.class, "answerShippingQuery");
        register(AnswerPreCheckoutQuery.class, "answerPreCheckoutQuery");

        register(GetMe.class, "getMe");
        register(GetUserProfilePhotos.class, "getUserProfilePhotos");

        register(SetGameScore.class, "setGameScore");
        register(GetGameHighScores.class, "getGameHighScores");

        register(SetWebhook.class, "setWebhook");
        register(DeleteWebhook.class, "deleteWebhook");
        register(GetWebhookInfo.class, "getWebhookInfo");

        register(StopPoll.class, "stopPoll");

    }

    public static void register(Class<?> clazz, String methodName) {
        METHOD_NAMES.put(clazz, methodName);
    }

    public static String getMethodName(Object methodObject) {
        String name = METHOD_NAMES.get(methodObject.getClass());
        if (name == null) {
            throw new IllegalArgumentException("Unknown method class: " + methodObject.getClass().getName());
        }
        return name;
    }
}
