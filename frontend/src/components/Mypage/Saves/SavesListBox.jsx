import styled from 'styled-components';
import TagList from './TagList';
import AnswerList from './AnswerList';
import UserInfo from './UserInfo';
import ListAdditionalInfo from './ListAdditionalInfo';

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
				} = bookmark;
				console.log(answer);

				let splitDate = createdAt.split(' ');
				let date = `${splitDate[1]} ${splitDate[2]}, ${splitDate[3]} at ${splitDate[4]}`;
				return (
					<ListBox key={questionId}>
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
						{answer ? <AnswerList answer={answer} /> : null}
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
		font-size: 1.15rem;
		margin-top: -0.15rem;
		margin-bottom: 0.38rem;
		padding-right: 24px;
		font-weight: 600;
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
