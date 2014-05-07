package com.gtt.pets.service.impl.account;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.account.AccountDTO;
import com.gtt.pets.bean.account.ThirdUserDTO;
import com.gtt.pets.dao.account.AccountDao;
import com.gtt.pets.dao.account.ThirdUserDao;
import com.gtt.pets.entity.account.AccountEntity;
import com.gtt.pets.entity.account.ThirdUserEntity;
import com.gtt.pets.service.account.AccountService;
import com.gtt.pets.util.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author tiantiangao
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private ThirdUserDao thirdUserDao;

	@Override
	public int addAccount(String nickname, String email, int thirdType, String thirdId, String token, String userIP) {
		try {
			// check arguments
			Preconditions.checkArgument(isNotBlank(nickname));
			Preconditions.checkArgument(thirdType > 0);
			Preconditions.checkArgument(isNotBlank(thirdId));
			Preconditions.checkArgument(isNotBlank(token));
			Preconditions.checkArgument(isNotBlank(userIP));

			// add account
			int accountId = accountDao.addAccount(nickname, email, userIP);
			Preconditions.checkArgument(accountId > 0);

			// add third user
			int result = thirdUserDao.addThirdUser(thirdType, thirdId, token, accountId);
			Preconditions.checkArgument(result > 0);

			LOGGER.info("addAccount: nickname = [" + nickname + "], thirdType = [" + thirdType + "], thirdId = [" +
					thirdId + "], token = [" + token + "], userIP = [" + userIP + "]");
			return accountId;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public AccountDTO loadByAccountID(int accountId) {
		try {
			// check arguments
			Preconditions.checkArgument(accountId > 0);

			// load account
			AccountEntity accountEntity = accountDao.loadById(accountId);

			// to dto
			return DTOUtils.toDTO(AccountDTO.class, accountEntity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AccountDTO loadByThirdId(int thirdType, String thirdId) {
		try {
			// check arguments
			Preconditions.checkArgument(thirdType > 0);
			Preconditions.checkArgument(isNotBlank(thirdId));

			// load third user
			ThirdUserEntity thirdUserEntity = thirdUserDao.loadByThirdID(thirdType, thirdId);
			if (thirdUserEntity == null) {
				return null;
			}

			// load account
			return loadByAccountID(thirdUserEntity.getAccountId());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AccountDTO loadByNickname(String nickname) {
		try {
			// check arguments
			Preconditions.checkArgument(isNotBlank(nickname));

			// load account
			AccountEntity accountEntity = accountDao.loadByNickname(nickname);

			// to dto
			return DTOUtils.toDTO(AccountDTO.class, accountEntity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ThirdUserDTO loadByAccountID(int accountId, int thirdType) {
		try {
			// check arguments
			Preconditions.checkArgument(accountId > 0);
			Preconditions.checkArgument(thirdType > 0);

			// load third user
			ThirdUserEntity thirdUserEntity = thirdUserDao.loadByAccountID(thirdType, accountId);
			if (thirdUserEntity == null) {
				return null;
			}

			// to dto
			return DTOUtils.toDTO(ThirdUserDTO.class, thirdUserEntity);
		} catch (Exception e) {
			return null;
		}
	}
}
