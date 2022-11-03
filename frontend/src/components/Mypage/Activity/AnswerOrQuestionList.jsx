import styled from 'styled-components';

const AnswerOrQuestionList = ({ lists, limit }) => {
	return (
		<Container>
			{lists.map((list, i) => {
				const { questionId, choosed, createdAt, title, url, vote } = list;
				const days = createdAt.split(' ');
				let date = `${days[1]} ${days[2]}, ${days[3]}`;

				if (limit && i >= limit) {
					return;
				}

				return (
					<Box key={questionId} limit={limit}>
						<ContentBox>
							<LeftContent>
								<VoteDisplay choosed={choosed}>{vote}</VoteDisplay>
								<Title href={url}>{title}</Title>
							</LeftContent>
							<DateText>{date}</DateText>
						</ContentBox>
					</Box>
				);
			})}
		</Container>
	);
};

export default AnswerOrQuestionList;

const Container = styled.div`
	flex-grow: 1;
	display: flex;
	flex-direction: column;
`;

const Box = styled.div`
	border-bottom: 1px solid #d7d9dc;
	padding: 12px;
	&:nth-child(props.limit) {
		border-bottom: none;
	}
	&:last-child {
		border-bottom: none;
	}
`;

const ContentBox = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin: calc(var(12px) / 2 * -1);
	margin-top: 0;
	margin-bottom: 0;
`;

const LeftContent = styled.div`
	display: flex;
	align-items: center;
`;

const VoteDisplay = styled.div`
	border: ${(props) => (props.choosed ? 'none' : '1px solid #c8ccd0')};
	background-color: ${(props) => (props.choosed ? '#5DBA7D' : 'white')};
	color: ${(props) => (props.choosed ? 'white' : 'black')};
	font-weight: 400;
	min-width: 30px;
	display: inline-flex;
	align-items: center;
	justify-content: center;
	padding: 0 6px;
	border-radius: 3px;
	font-size: 12px;
	line-height: 2;
	text-decoration: none;
	white-space: nowrap;
`;

const Title = styled.a`
	text-decoration: none;
	display: -webkit-box;
	color: #0063bf;
	font-size: 15px;
	overflow: hidden;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 1;
	line-height: 1.3;
	word-break: break-all;
	overflow-wrap: break-word;
	hyphens: auto;
	font-weight: 400;
	margin: 0 12px;
	:hover {
		color: #0a95ff;
	}
`;

const DateText = styled.span`
	font-size: 13px;
	color: #6a737c;
	white-space: nowrap;
	font-weight: 400;
`;
