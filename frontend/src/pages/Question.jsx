import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import Editor from '../components/common/Editor';
import Controller from '../components/Question/Controller';
import Button from '../components/common/Button';
import {
	answer,
	commentA,
	commentQ,
	commentQDEL,
	commentADEL,
	getQuestion,
	getUserInfo,
} from '../api/QuestionApi';
import timeForToday from '../utils/timeForToday';

const Question = () => {
	const [thread, setThread] = useState('');
	const [nickname, setNickname] = useState('');
	const [answerData, setAnswerData] = useState('');
	const { questionId } = useParams();
	useEffect(() => {
		getUserInfo().then((res) => setNickname(res.nickname));
	}, []);
	useEffect(() => {
		getQuestion(questionId).then((res) => setThread(res));
	}, []);

	const handleCommentQ = (e) => {
		if (e.key === 'Enter') {
			//test
			const arr = [...thread.comments];
			const test = {
				id: thread.comments.length + 1,
				comments: e.target.value,
				createdAt: '2022-11-03T23:20:27.362238',
				nickname: 'gildong',
			};
			arr.push(test);
			const comments = { comments: arr };
			const data = Object.assign({}, thread, comments);

			//real
			// const data = { body: e.target.value, questionId };

			commentQ(data);
			e.target.value = '';
			getQuestion(questionId).then((res) => setThread(res)); // 새로고침해야 댓글 보이...면... 그 뒤에 생각하자.
		}
	};

	const handleCommentA = (e, answerId) => {
		if (e.key === 'Enter') {
			//test
			const arr = thread.answers.filter((ele) => {
				return ele.answerId === answerId;
			});
			const test = {
				id: thread.answers.comments?.length + 1,
				comments: e.target.value,
				createdAt: '2022-11-03T23:20:29.553901',
				nickname: 'gildong',
			};
			arr[0].comments.push(test);
			const answers = { answers: arr };
			const data = Object.assign({}, thread, answers);

			//real
			// const data = { body: e.target.value, answerId, questionId };
			commentA(data);
			e.target.value = '';
			getQuestion(questionId).then((res) => setThread(res));
		}
	};
	const handleDeleteCommentQ = (comments, idx) => {
		const head = comments.slice(0, idx);
		const tail = comments.slice(idx + 1);
		const result = head.concat(tail);
		const data = { result: result };
		commentQDEL(JSON.stringify(data));
		getQuestion(questionId).then((res) => setThread(res));
	};
	const handleDeleteCommentA = (comments, idx) => {
		const head = comments.slice(0, idx);
		const tail = comments.slice(idx + 1);
		const result = head.concat(tail);
		const data = { result: result };
		commentADEL(JSON.stringify(data));
		getQuestion(questionId).then((res) => setThread(res));
	};
	const handleAnswer = (str) => {
		setAnswerData({
			body: str,
		});
	};
	const handleSubmitAnswer = () => {
		//test
		const arr = [...thread.answers];
		const test = {
			answerId: thread.answers.length + 1,
			contents: answerData.body,
			vote: -1,
			chooseYn: false,
			createdAt: '2022-11-03T23:20:22.798088',
			modifiedAt: '2022-11-03T23:20:22.798088',
			nickname: 'gildong',
			comments: [],
			bookmarks: [],
			answerLikes: [
				{
					nickname: 'gildong',
					userId: 1,
					likeYn: true,
					unlikeYn: false,
				},
			],
		};
		arr.push(test);
		const answers = { answers: arr };
		const data = Object.assign({}, thread, answers);
		answer(data);
		getQuestion(questionId).then((res) => setThread(res));

		//real
		// const data = { body: answerData.body, questionId };
		// answer(data);
	};

	return (
		thread && (
			<>
				<Wrapper>
					<QuestionGroup>
						<Header>
							<Title>{thread.title}</Title>
							<Btn>
								<Link to="/questions/ask">
									<Button text="Ask Question" />
								</Link>
							</Btn>
							<Info>
								Asked
								<span>{' ' + timeForToday(thread.createdAt) + ' ago'}</span>
								Viewed<span>{' ' + thread.views + ' times'}</span>
							</Info>
							<hr />
						</Header>
						<QuestionContainer>
							<Left>
								<Controller
									bookmarkdata={thread.bookmarks}
									votecount={thread.vote}
									votedata={thread.questionsLikes}
									QcreatorNickname={thread.nickname}
									loginNickname={nickname}
									questionId={questionId}></Controller>
							</Left>
							<Right>
								<Body
									dangerouslySetInnerHTML={{
										__html: thread.contents,
									}}></Body>
								<Tags>
									{thread.questionsTags.map((el) => (
										<span key={el.tagId}>{el.title}</span>
									))}
								</Tags>

								<Footer>
									<Options>
										<span>Share</span>
										{nickname === thread.nickname && <span>Edit</span>}
									</Options>
									<History>
										{!isNaN(timeForToday(thread.modifiedAt)) &&
											'Edited ' + timeForToday(thread.modifiedAt) + ' ago'}
									</History>
									<Profile>
										<div></div>
										<span>{thread.nickname}</span>
									</Profile>
								</Footer>
								{thread.comments && <hr />}
								{thread.comments &&
									thread.comments.map((c, idx) => (
										<Grouper key={c.id}>
											<Comments>
												<span>{c.comments} – </span>
												<span className="nickname">{c.nickname}</span>
												<span className="createdAt">
													{' ' + timeForToday(c.createdAt) + ' ago'}
												</span>
												{nickname === c.nickname && (
													<DEL
														className="delete"
														onClick={() =>
															handleDeleteCommentQ(thread.comments, idx)
														}>
														×
													</DEL>
												)}
											</Comments>
											<hr />
										</Grouper>
									))}
								<CommentCreate
									onKeyDown={handleCommentQ}
									placeholder="Add a comment"
								/>
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
								<Grouper key={el.answerId}>
									<AnswerContainer>
										<Left>
											<Controller
												kind="answer"
												bookmarkdata={el.bookmarks}
												votecount={el.vote}
												votedata={el.answerLikes}
												chose={el.choosed}
												QcreatorNickname={thread.nickname}
												loginNickname={nickname}
												questionId={questionId}
												answerId={el.answerId}></Controller>
										</Left>
										<Right>
											<Body
												dangerouslySetInnerHTML={{
													__html: el.contents,
												}}></Body>
											<Footer>
												<Options>
													<span>Share</span>
													{nickname === el.nickname && <span>Edit</span>}
												</Options>
												<History>
													{!isNaN(timeForToday(thread.modifiedAt)) &&
														'Edited ' +
															timeForToday(thread.modifiedAt) +
															' ago'}
												</History>
												<Profile>
													<div></div>
													<span>{el.nickname}</span>
												</Profile>
											</Footer>
											{el.comments && <hr />}
											{el.comments &&
												el.comments.map((c, idx) => (
													<Grouper key={c.id}>
														<Comments>
															<span>{c.comments} – </span>
															<span className="nickname">{c.nickname}</span>
															<span className="createdAt">
																{' ' + timeForToday(c.createdAt) + ' ago'}
															</span>
															{nickname === c.nickname && (
																<DEL
																	className="delete"
																	onClick={() =>
																		handleDeleteCommentA(el.comments, idx)
																	}>
																	×
																</DEL>
															)}
														</Comments>
														<hr />
													</Grouper>
												))}
											<CommentCreate
												onKeyDown={() => handleCommentA(event, el.answerId)}
												placeholder="Add a comment"
											/>
										</Right>
									</AnswerContainer>
									<hr />
								</Grouper>
							))}
					</AnswerGroup>

					<EditGroup>
						<YourAnswer>Your Answer</YourAnswer>
						<Editor callback={handleAnswer} />
						<Button text="Post Your Answer" callback={handleSubmitAnswer} />
					</EditGroup>
				</Wrapper>
			</>
		)
	);
};
const Wrapper = styled.section`
	position: relative;
	max-width: 1080px;
	padding: 1.5rem 1rem 1.5rem 1.5rem;
	hr {
		margin: 1rem 0 1rem;
		height: 1px;
		background-color: rgb(187, 191, 195);
		border: none;
	}
`;
const Grouper = styled.div``;
const QuestionGroup = styled.article``;

const Header = styled.div``;
const Title = styled.h1`
	width: 100%;
	font-size: 1.75rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 0.25rem;
`;
const Btn = styled.div`
	position: absolute;
	right: 1rem;
	top: 2rem;
`;
const Info = styled.div`
	font-size: 0.8rem;
	margin-right: 1rem;
	color: rgb(108, 115, 123);
	span {
		color: black;
		margin-right: 1rem;
	}
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
	/* 이하 Editor의 Result 컴포넌트와 같음*/
	ul {
		list-style-type: disc;
		margin-left: 1rem;
	}
	ol {
		list-style-type: decimal;
		margin-left: 1rem;
	}

	h1 {
		font-weight: 700;
		font-size: 1.25rem;
	}
	h2 {
		font-weight: 700;
		font-size: 1.1rem;
	}
	h3 {
		font-weight: 700;
	}

	strong {
		font-weight: 700;
	}
	em {
		font-style: italic;
	}
	hr {
		height: 1px;
	}
	pre {
		font-size: 0.9rem;
		background-color: #f6f6f6;
		padding: 0.5rem;
		overflow-x: auto;
	}
	code {
		font-size: 0.9rem;
		background-color: #f6f6f6;
		padding: 0.1rem 0.3rem 0.1rem 0.3rem;
	}

	p {
		margin-bottom: 1rem;
	}
`;

const Tags = styled.div`
	font-size: 0.85rem;
	margin-bottom: 1rem;
	max-width: 62.25rem;
	span {
		margin-right: 0.5rem;
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
const DEL = styled.button`
	cursor: pointer;
	background: none;
	margin: 0 0.5rem 0 0.5rem;
`;
const CommentCreate = styled.input`
	width: 100%;
	padding: 1rem 1rem 1rem 0;
	font-size: 0.85rem;
	color: rgb(187, 191, 195);
	&:focus-within {
		outline: none;
	}
	&:focus {
		outline: none;
	}
	::placeholder {
		color: #bbbbbb;
	}
`;
const AnswerGroup = styled.div``;
const AnswerContainer = styled.article`
	display: grid;
	grid-template-columns: 3.25rem 1fr;
`;
const AnswerCount = styled.h2`
	font-size: 1.25rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 1rem;
`;

const EditGroup = styled.div`
	button {
		margin-top: 2rem;
	}
`;

const YourAnswer = styled.h2`
	font-size: 1.25rem;
	font-weight: 400;
	line-height: 140%;
	margin-bottom: 1rem;
`;
export default Question;
