import styled from 'styled-components';
import TagList from '../common/TagList';
import AnswerList from '../common/AnswerList';
import UserInfo from '../common/UserInfo';
import ListAdditionalInfo from '../common/ListAdditionalInfo';

const SavesListBox = ({ bookmarks }) => {
	return (
		<Container>
			{bookmarks?.map((bookmark) => {
				const {
					answerCount,
					choosed,
					createdAt,
					questionId,
					questionUser,
					tag,
					title,
					url,
					views,
					vote,
					answer,
					id,
				} = bookmark;

				let days = new Date(new Date(createdAt).getTime() + 9 * 60 * 60 * 1000)
					.toString()
					.split(' ');
				let date = `${days[1]} ${days[2]} at ${days[4]}`;
				return (
					<ListBox key={id}>
						<ListAdditionalInfo
							vote={vote}
							choosed={choosed}
							answerCount={answerCount}
							views={views}
						/>
						<ContentBox>
							<h3>
								<a href={url}>{title}</a>
							</h3>
							<TagAndUserInfoBox>
								<TagList tag={tag} />

								<UserInfo questionUser={questionUser} date={date} />
							</TagAndUserInfoBox>
						</ContentBox>
						{answer ? (
							<AnswerList answer={answer} postUrl={url} type="answer" />
						) : null}
					</ListBox>
				);
			})}
		</Container>
	);
};

export default SavesListBox;

const Container = styled.div`
	border: 1px solid #e4e6e8;
	border-radius: 5px;
`;

const ListBox = styled.div`
	display: flex;
	flex-direction: column;
	border-bottom: 1px solid #e4e6e8;
	padding: 16px;

	&:last-child {
		border: none;
	}
`;

const ContentBox = styled.div`
	width: 100%;
	flex-grow: 1;
	min-width: 100%;

	h3 {
		font-size: 1.05rem;
		margin-top: -0.15rem;
		margin-bottom: 0.38rem;
		padding-right: 24px;
		font-weight: 400;
		word-break: break-word;
		overflow-wrap: break-word;
		hyphens: auto;
		a {
			text-decoration: none;
			cursor: pointer;
			color: #0074cc;
			:hover {
				color: #0a95ff;
			}
		}
	}
`;

const TagAndUserInfoBox = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-wrap: wrap;
	column-gap: 6px;
	row-gap: 8px;
`;
