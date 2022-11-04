import styled from 'styled-components';
import { useState } from 'react';
import Editor from '../components/common/Editor';
import Button from '../components/common/Button';
import TagForm from '../components/Ask/TagForm';
import { ask } from '../api/QuestionApi';
import background from '../assets/images/background.svg';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
const Ask = ({ editTitle, editBody, editTag }) => {
	const [title, setTitle] = useState('');
	const [problem, setProblem] = useState('');
	const [expect, setExpect] = useState('');
	const [tagsarr, setTagsarr] = useState('');

	const handleClick = () => {
		const data = {
			title: title,
			body: problem + expect,
			tags: tagsarr,
		};
		ask(JSON.stringify(data));
	};

	const handleTitle = (e) => {
		setTitle(e.target.value);
	};
	const handleProblem = (str) => {
		setProblem(str);
	};
	const handleExpect = (str) => {
		setExpect(str);
	};
	const handleTags = (arr) => {
		setTagsarr(arr);
	};

	return (
		<>
			<Header />
			<Container>
				{/* <Formarea action="/question/ask" method="post"> */}
				<AskHeader>
					<AskQuestion>Ask a public question</AskQuestion>
				</AskHeader>
				<BodyConatiner>
					<WritingTip>
						<h2>Writing a good question</h2>
						<p className="writingtip">
							You’re ready to ask a programming-related question and this form
							will help guide you through the process. <br />
							Looking to ask a non-programming question? See{' '}
							<a href="https://stackexchange.com/sites#technology">
								the topics here
							</a>{' '}
							to find a relevant site.
						</p>
						<p className="steps">Steps</p>
						<ul>
							<li>Summarize your problem in a one-line title.</li>
							<li>Describe your problem in more detail.</li>
							<li>Describe what you tried and what you expected to happen.</li>
							<li>
								Add “tags” which help surface your question to members of the
								community.
							</li>
							<li>Review your question and post it to the site.</li>
						</ul>
					</WritingTip>
					<Group>
						<SectionTitle>
							<label htmlFor="title">Title</label>
						</SectionTitle>
						<Caption>
							<label htmlFor="title">
								Be specific and imagine you’re asking a question to another
								person.
							</label>
						</Caption>
						<Form
							id="title"
							name="title"
							type="text"
							maxlength="300"
							placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
							onChange={handleTitle}
						/>
					</Group>
					<Tip></Tip>
					<Group>
						<SectionTitle>What are the details of your problem?</SectionTitle>
						<Caption>
							Introduce the problem and expand on what you put in the title.
							Minimum 20 characters.
						</Caption>
						<Editor callback={handleProblem} />
					</Group>
					<Tip></Tip>
					<Group>
						<SectionTitle>
							What did you try and what were you expecting?
						</SectionTitle>
						<Caption>
							Describe what you tried, what you expected to happen, and what
							actually resulted. Minimum 20 characters.
						</Caption>
						<Editor callback={handleExpect} />
					</Group>
					<Tip></Tip>
					<Group>
						<SectionTitle>Tags</SectionTitle>
						<Caption>
							Add up to 5 tags to describe what your question is about. Start
							typing to see suggestions.
						</Caption>
						<TagForm callback={handleTags} />
					</Group>
					<Button text="Review your question" callback={handleClick} />
				</BodyConatiner>
				{/* </Formarea> */}
			</Container>
			<Footer />
		</>
	);
};

const Container = styled.section`
	background-color: #f8f9f9;
	width: 100vw;
`;
// const Formarea = styled.form`
// 	max-width: 850px;
// `;

const AskHeader = styled.div`
	height: 10rem;
	margin-right: 1.5rem;
	@media screen and (min-width: 750px) {
		background-image: url('${background}');
		background-size: 573px;
		background-repeat: no-repeat;
		background-position: right top;
	}
`;
const BodyConatiner = styled.div`
	padding: 0 4.5rem 4.5rem 4.5rem;
	max-width: 860px;
`;

const AskQuestion = styled.h1`
	font-weight: 600;
	font-size: 1.75rem;
	padding: 3rem 0 4rem 4.5rem;
`;
const WritingTip = styled.div`
	background-color: #edf4fa;
	border: 1px solid #aecdea;
	border-radius: 2px;
	line-height: 125%;
	padding: 1.75rem;
	margin-bottom: 1.5rem;
	h2 {
		font-weight: 400;
		font-size: 1.3rem;
		margin-bottom: 1rem;
	}
	.writingtip {
		font-size: 0.9rem;
		margin-bottom: 1rem;
	}
	.steps {
		font-size: 0.85rem;
		margin-bottom: 0.9rem;
		font-weight: 600;
	}
	ul li {
		font-size: 0.8rem;
		list-style-type: disc;
		margin-left: 2rem;
	}
	a {
		text-decoration: none;
		color: rgb(41, 95, 166);
	}
`;
const Group = styled.div`
	background-color: white;
	border: 1px solid #dde0e1;
	padding: 1.75rem;
	margin-bottom: 1.5rem;
`;
const SectionTitle = styled.h3`
	font-size: 0.95rem;
	font-weight: 600;
	line-height: 115%;
	margin-bottom: 0.3rem;
`;
const Caption = styled.div`
	font-size: 0.75rem;
	margin-bottom: 0.6rem;
`;
const Tip = styled.div``;
const Form = styled.input`
	width: 100%;
	height: 30px;
	font-size: 0.85rem;
	font-weight: 500;
	border: 1px solid rgb(179, 183, 188);
	padding-left: 0.5rem;
	border-radius: 3px;
	background-color: white;
	display: flex;
	align-items: center;
	justify-content: center;

	&:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
	&:focus {
		outline: none;
	}
	::placeholder {
		color: #bbbbbb;
	}
`;
// const DiscardDraft = styled.button``;
export default Ask;
