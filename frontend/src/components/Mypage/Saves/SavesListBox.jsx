import styled from 'styled-components';
import { GoCheck } from 'react-icons/go';
import TagList from './TagList';
import AnswerList from './AnswerList';
import defaultImage from '../../../assets/images/userDefaultImage.png';

const SavesListBox = ({ bookmarks }) => {
	return (
		<Container>
			{bookmarks?.map((bookmark, i) => {
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
					<ListBox key={i}>
						<ListDetailInfoWrapper>
							<VotesBox>
								<span>{vote}</span>
								<span>votes</span>
							</VotesBox>
							<AnswerBox choosed={choosed}>
								{choosed ? <GoCheck style={{ color: 'white' }} /> : null}
								<span>{answerCount}</span>
								<span>answer</span>
							</AnswerBox>
							<ViewsBox>
								<span>{views}</span>
								<span>views</span>
							</ViewsBox>
						</ListDetailInfoWrapper>
						<ContentBox>
							<h3>
								<a href={url}>{title}</a>
							</h3>
							<TagAndUserInfoBox>
								<TagBox>
									<ul>
										<TagList tag={tag} />
									</ul>
								</TagBox>
								<UserInfoBox>
									<UserImage src={defaultImage} alt="user-image" />
									<a href="1">{questionUser}</a>
									<time>asked</time>
									<span>{date}</span>
								</UserInfoBox>
							</TagAndUserInfoBox>
						</ContentBox>
						{answer ? (
							<AnswerList
								answer={answer}
								choosed={choosed}
								answerCount={answerCount}
							/>
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

const ListDetailInfoWrapper = styled.div`
	width: auto;
	flex-direction: row;
	align-items: center;
	gap: 6px;
	margin-right: 16px;
	margin: 4px 4px 8px 0;
	display: flex;
	flex-shrink: 0;
	flex-wrap: wrap;
	font-size: 14px;
	color: #6a737c;
`;

const VotesBox = styled.div`
	color: #0c0d0e;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;
	border: 1px solid transparent;

	span {
		font-weight: 600;
		font-size: 14px;
		&:first-child {
			font-weight: 700;
		}
	}
`;

const AnswerBox = styled.div`
	color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	background-color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	border: 1px solid #2e6f44;
	border-radius: 3px;
	padding: 4px;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;

	span {
		color: ${(props) => (props.choosed ? 'white' : '#2E6F44')};
		font-weight: 600;
		&:first-child {
			font-weight: 700;
		}
	}
`;

const ViewsBox = styled.div`
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;
	border: 1px solid transparent;

	span {
		font-weight: 600;
		&:first-child {
			font-weight: 700;
		}
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

const TagBox = styled.div`
	display: flex;
	flex-wrap: wrap;
	gap: 4px;

	ul {
		display: inline;
		list-style: none;
		margin-left: 0;
		margin-bottom: 0.8rem;
	}
`;

const UserInfoBox = styled.div`
	display: flex;
	justify-content: flex-end;
	align-items: center;
	gap: 4px;
	flex-wrap: wrap;
	margin-left: auto;
	grid-template-columns: auto 1fr;
	line-height: 1;
	a {
		margin: 2px;
		color: #0074cc;
		text-decoration: none;
		cursor: pointer;
		font-size: 13px;
		font-weight: 600;
		:hover {
			color: #0a95ff;
		}
	}
	time {
		color: #6a737c;
		font-size: 13px;
		font-weight: 600;
	}
	span {
		color: #6a737c;
		font-size: 13px;
		font-weight: 600;
	}
`;

// const AnswerListBox = styled.div`
// 	position: relative;
// 	margin: 16px 1rem 0 1rem;
// 	padding: 0.5rem 0 0.5rem 1rem;
// 	::before {
// 		content: '';
// 		display: block;
// 		position: absolute;
// 		top: 0;
// 		left: 0;
// 		right: 0;
// 		width: 4px;
// 		height: 100%;
// 		border-radius: 8px;
// 		background-color: #c8ccd0;
// 	}
// `;

// const AnswerVoteAnswerBox = styled.div`
// 	display: flex;
// `;

// const AnswerContent = styled.p`
// 	color: #525960;
// 	display: -webkit-box;
// 	-webkit-line-clamp: 4;
// 	-webkit-box-orient: vertical;
// 	overflow: hidden;
// 	margin-bottom: 8px;
// 	font-size: 15px;
// 	font-weight: 500;
// 	margin-left: 8px;
// `;

// const AnswerAndUserInfoBox = styled(TagAndUserInfoBox)`
// 	margin-top: 8px;
// 	a {
// 		text-decoration: none;
// 		cursor: pointer;
// 		color: #0074cc;
// 		font-size: 14px;
// 		font-weight: 500;
// 		:hover {
// 			color: #0a95ff;
// 		}
// 	}
// `;

const UserImage = styled.img`
	border-radius: 3px;
	width: 16px;
	height: 16px;
`;
