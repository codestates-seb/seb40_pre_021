import styled from 'styled-components';
import { useEffect, useState } from 'react';
import Editor from '../components/common/Editor';
import Controller from '../components/Question/Controller';
import Button from '../components/common/Button';
import { getQuestion, getUserInfo } from '../api/QuestionApi';
import useDate from '../hooks/useDate';

const { nickname, createdAt } = getUserInfo();

const Question = () => {
	const [thread, setThread] = useState('');
	useEffect(() => {
		getQuestion().then((res) => setThread(res));
	}, []);

	const [Qdate] = useDate(thread.createdAt);

	const Qcreator = nickname === thread.nickname ? true : false;
	const Acreator = nickname === thread.nickname ? true : false;

	return (
		thread && (
			<>
				<Wrapper>
					<QuestionGroup>
						<Header>
							<Title>{thread.title}</Title>
							<Btn>
								<Button text="Ask Question" />
							</Btn>

							<Info>Asked {Qdate}</Info>
							<hr />
						</Header>
						<QuestionContainer>
							<Left>
								<Controller
									QcreatorNickname={thread.nickname}
									loginNickname={nickname}></Controller>
							</Left>
							<Right>
								<Body
									dangerouslySetInnerHTML={{
										__html: thread.contents,
									}}></Body>
								<Tags>{thread.questionTags[0].title}</Tags>
								<Footer>
									<Options>
										<span>Share</span>
										{nickname === thread.nickname && <span>Edit</span>}
									</Options>
									<History>Edited {thread.createdAt} Hours ago</History>
									<Profile>
										<div></div>
										<span>{thread.nickname}</span>
									</Profile>
								</Footer>
								{thread.comments && <hr />}
								{thread.comments &&
									thread.comments.map((c) => (
										<>
											<Comments key={c.commentId}>
												<span>{c.commentBody} – </span>
												<span className="nickname">{c.nickname}</span>
												<span className="createdAt">
													{' '}
													{c.createdAt} hours ago
												</span>
												{nickname === c.nickname && (
													<span className="delete"> × </span>
												)}
											</Comments>
											<hr />
										</>
									))}
								<CommentCreate>Add a comment</CommentCreate>
							</Right>
						</QuestionContainer>
					</QuestionGroup>
					<hr />
					<AnswerGroup>
						<Header>
							<AnswerCount>{thread.answers.length} Answers</AnswerCount>
						</Header>
						{thread.answers &&
							thread.answers.map((el) => (
								<>
									<AnswerContainer>
										<Left>
											<Controller
												kind="answer"
												choosed={el.choosed}
												QcreatorNickname={thread.nickname}
												loginNickname={nickname}></Controller>
										</Left>
										<Right>
											<Body
												dangerouslySetInnerHTML={{
													__html: el.body,
												}}></Body>
											<Footer>
												<Options>
													<span>Share</span>
													{nickname === el.nickname && <span>Edit</span>}
												</Options>
												<History>Edited {el.createdAt} Hours ago</History>
												<Profile>
													<div></div>
													<span>{el.nickname}</span>
												</Profile>
											</Footer>
											{el.comments && <hr />}
											{el.comments &&
												el.comments.map((c) => (
													<>
														<Comments key={c.commentId}>
															{c.commentBody} - {c.nickname}
														</Comments>
														<hr />
													</>
												))}
											<CommentCreate>Add a comment</CommentCreate>
										</Right>
									</AnswerContainer>
									<hr />
								</>
							))}
					</AnswerGroup>

					<EditGroup>
						<YourAnswer>Your Answer</YourAnswer>
						<Editor />
						<Button text="Post Your Answer" />
					</EditGroup>
				</Wrapper>
			</>
		)
	);
};
const Wrapper = styled.section`
	position: relative;
	max-width: 1080px;
	padding: 1.5rem 1rem 1.5rem 1rem;
	hr {
		margin: 1rem 0 1rem;
		height: 1px;
		background-color: rgb(187, 191, 195);
		border: none;
	}
`;

const QuestionGroup = styled.article``;

const Header = styled.div``;
const Title = styled.h1`
	width: calc(1080px - 3rem);
	font-size: 1.75rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 1rem;
`;
const Btn = styled.div`
	position: absolute;
	right: 1rem;
	top: 2rem;
`;
const Info = styled.div`
	font-size: 0.85rem;
`;

const QuestionContainer = styled.div`
	display: grid;
	grid-template-columns: 3.25rem 1fr;
`;
const Left = styled.div``;
const Right = styled.div`
	hr {
		/* Comment hr tag */
		margin: 0.25rem 0 0.25rem;
		background-color: rgb(241, 242, 242);
	}
`;
const Body = styled.div`
	width: auto;
	max-width: 62.25rem;
	line-height: 180%;
	margin-bottom: 1rem;
`;

const Tags = styled.div`
	width: auto;
	font-size: 0.85rem;
	margin-bottom: 1rem;
	max-width: 62.25rem;
	span {
		color: rgb(61, 123, 201);
		background-color: rgb(227, 236, 243);
		padding: 0.1rem 0.3rem 0.1rem 0.3rem;
		border-radius: 3px;
	}
`;
const Footer = styled.div`
	padding: 3rem 0 1rem;
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
`;

const Options = styled.div`
	font-size: 0.85rem;
	color: rgb(108, 115, 123);
	span {
		margin-right: 1rem;
	}
`;
const History = styled.span`
	font-size: 0.85rem;
	color: rgb(108, 115, 123);
`;

const Profile = styled.div`
	font-size: 0.85rem;
`;

const Comments = styled.div`
	font-size: 0.85rem;
	padding: 0 0 0 1.5rem;
	line-height: 180%;
	.nickname {
		color: rgb(61, 123, 201);
		background-color: rgb(227, 236, 243);
		padding: 0.1rem 0.3rem 0.1rem 0.3rem;
		border-radius: 3px;
	}
	.createdAt {
		color: rgb(108, 115, 123);
	}
`;
const CommentCreate = styled.div`
	padding: 1rem 0 0 0;
	font-size: 0.85rem;
	color: rgb(187, 191, 195);
`;
const AnswerGroup = styled.div``;
const AnswerContainer = styled.article`
	display: grid;
	grid-template-columns: 68px 1fr;
`;
const AnswerCount = styled.h2`
	font-size: 1.25rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 1rem;
`;

const EditGroup = styled.div``;
const YourAnswer = styled.h2`
	font-size: 1.25rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 1rem;
`;
export default Question;
