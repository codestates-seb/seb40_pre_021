import styled from 'styled-components';

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
				return (
					<ListBox key={i}>
						<ListDetailInfoWrapper>
							<VotesBox>
								<span>{vote}</span>
								<span>votes</span>
							</VotesBox>
							<AnswerBox>
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
										{tag.map((item) => {
											return (
												<TagList key={item}>
													<a href="123">{item}</a>
												</TagList>
											);
										})}
									</ul>
								</TagBox>
								<UserInfoBox>
									<a href="1">{questionUser}</a>
									<time>asked</time>
									<span>{createdAt}</span>
								</UserInfoBox>
							</TagAndUserInfoBox>
						</ContentBox>
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
		&:first-child {
			font-weight: 700;
		}
	}
`;

const AnswerBox = styled.div`
	color: #2e6f44;
	border: 1px solid #2e6f44;
	border-radius: 3px;
	padding: 4px;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;

	span {
		color: #2e6f44;
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

const TagList = styled.li`
	display: inline;
	margin: 4px 4px 4px 0;

	a {
		display: inline-block;
		font-size: 12px;
		color: #39739d;
		background-color: #e1ecf4;
		padding: 0.4rem 0.5rem;
		margin: 2px 2px 2px 0;
		line-height: 1;
		white-space: nowrap;
		text-decoration: none;
		text-align: center;
		border: 1px solid transparent;
		border-radius: 3px;
		font-weight: 600;
		:hover {
			background-color: #d1e3f0;
			color: #2c5877;
		}
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
		font-size: 14px;
		font-weight: 600;
		:hover {
			color: #0a95ff;
		}
	}
	time {
		color: #6a737c;
		font-size: 14px;
		font-weight: 600;
	}
	span {
		color: #6a737c;
		font-size: 14px;
		font-weight: 600;
	}
`;
